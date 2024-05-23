package io.ssafy.userservice.user.member.service;


import io.ssafy.userservice.domain.entity.MailRedis;
import io.ssafy.userservice.domain.repository.MailRedisRepository;
import io.ssafy.userservice.global.response.Response;
import io.ssafy.userservice.oauth2.jwt.JwtTokenProvider;
import io.ssafy.userservice.user.member.entity.Member;
import io.ssafy.userservice.user.member.respository.MemberRepository;
import io.ssafy.userservice.user.mycostume.repository.MyCostumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static io.ssafy.userservice.global.response.Response.ERROR;
import static io.ssafy.userservice.global.response.Response.OK;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailRedisRepository mailRedisRepository;


    @Override
    public Response<?> withdrawalMember(String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            member.deleteMember();
            memberRepository.save(member);
            return OK("정상적으로 탈퇴되었습니다.");
        } else {
            return ERROR("등록된 회원이 아닙니다.", HttpStatus.BAD_REQUEST);
        }

    }


}
