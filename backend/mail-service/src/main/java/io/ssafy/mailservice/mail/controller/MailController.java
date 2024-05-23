package io.ssafy.mailservice.mail.controller;

import io.micrometer.core.annotation.Timed;
import io.ssafy.mailservice.mail.dto.SendMailReqDto;
import io.ssafy.mailservice.mail.dto.VerifyCodeReqDto;
import io.ssafy.mailservice.mail.service.MailService;
import io.ssafy.mailservice.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
@Slf4j
@Tag(name = "메일 도메인", description = "메일 API")
public class MailController {


    private final MailService mailService;

    @Timed(value = "mail.send", longTask = true)
    @Operation(summary = "메일 전송")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "메일 전송 성공")
        }
    )
    @PostMapping("/send")
    public Response<?> sendEmail(@RequestBody SendMailReqDto sendMailReqDto) {
        return mailService.sendEmail(sendMailReqDto.getEmail(), sendMailReqDto.getMemberNickname());
    }

    @Timed(value = "mail.resend", longTask = true)
    @Operation(summary = "메일 재전송")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "메일 재전송 성공")
        }
    )
    @PostMapping("/resend")
    public Response<?> reSendMail(@RequestBody SendMailReqDto sendMailReqDto) {
        return mailService.reSendEmail(sendMailReqDto.getEmail(), sendMailReqDto.getMemberNickname());
    }

    @Timed(value = "mail.verify", longTask = true)
    @Operation(summary = "메일 코드 인증")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "메일 코드 인증 성공"),
            @ApiResponse(responseCode = "400", description = "메일 코드 인증 실패")
        }
    )
    @PostMapping("/verify")
    public Response<?> verifyCode(@RequestBody VerifyCodeReqDto verifyCodeReqDto) {
        return mailService.verifyCode(verifyCodeReqDto.getEmail(), verifyCodeReqDto.getCode());
    }
}
