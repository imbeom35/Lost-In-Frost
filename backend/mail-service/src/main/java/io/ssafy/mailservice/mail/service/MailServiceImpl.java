package io.ssafy.mailservice.mail.service;

import io.ssafy.mailservice.mail.entity.Mail;
import io.ssafy.mailservice.mail.entity.MailRedis;
import io.ssafy.mailservice.mail.repository.MailRedisRepository;
import io.ssafy.mailservice.mail.repository.MailRepository;
import io.ssafy.mailservice.response.Response;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

import static io.ssafy.mailservice.response.Response.ERROR;
import static io.ssafy.mailservice.response.Response.OK;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private final JavaMailSender javaMailSender;
    private final MailRepository mailRepository;
    private final MailRedisRepository mailRedisRepository;

    @Value("${spring.mail.username}")
    private String senderEmail;



    @Override
    public Response<?> sendEmail(String email, String nickname) {
        int code = createNumber();
        MimeMessage mimeMessage = createMessage(email, code);
        if (mimeMessage == null) {
            return ERROR("메일 전송에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        try {
            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            return ERROR("메일 전송에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        MailRedis mailRedis = new MailRedis(email, nickname, code);

        mailRedisRepository.save(mailRedis);
        return OK(null);
    }

    @Override
    public Response<?> reSendEmail(String email, String nickname) {
        Mail mail = mailRepository.findByEmail(email).orElse(null);
        if (mail == null) {
           return ERROR("메일이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        int code = createNumber();
        MimeMessage mimeMessage = createMessage(email, code);
        if (mimeMessage == null) {
            return ERROR("메일 전송에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        javaMailSender.send(mimeMessage);
        mail.updateCode(code);
        mailRepository.save(mail);
        return OK(null);
    }

    @Override
    public Response<?> verifyCode(String email, int code) {
        MailRedis mail = mailRedisRepository.findById(email).orElse(null);
        if (mail == null) {
            return ERROR("메일이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        if (mail.getCode() == code) {
            return OK(null);
        } else {
            return ERROR("인증번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }


    private int createNumber(){
        RandomGeneratorFactory<RandomGenerator> factory = RandomGeneratorFactory.of("SHA2");
        RandomGenerator random = factory.create();
        return random.nextInt(100000, 999999); // 6자리 난수 생성
    }

    private MimeMessage createMessage(String email, int code) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            mimeMessage.setFrom(senderEmail);
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, email);
            mimeMessage.setSubject("Lost in Frost 인증 번호입니다.");
            String body = "";
            body += "<h1 style='font-size: 24px;'>[Lost in Frost]</h1>";
            body += "<p style='font-size: 18px;'>인증 번호는 <span style='color: #ff6b6b; font-weight: bold; font-size: 24px;'>"+ code +"</span> 입니다.</p>";
            body += "<p style='font-size: 13px;'>이 인증 번호는 5분 후에 만료됩니다. 인증 번호 만료 후에는 다시 회원가입을 진행해주세요!</p>";

            mimeMessage.setText(body, "utf-8", "html");
        } catch (MessagingException e) {
            return null;
        }
        return mimeMessage;
    }

}
