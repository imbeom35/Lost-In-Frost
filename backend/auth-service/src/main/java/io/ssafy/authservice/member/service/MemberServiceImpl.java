package io.ssafy.authservice.member.service;

import io.ssafy.authservice.auth.dto.request.MemberJoinReqDto;
import io.ssafy.authservice.auth.dto.request.MemberLoginReqDto;
import io.ssafy.authservice.entity.MailRedis;
import io.ssafy.authservice.entity.MyCostume;
import io.ssafy.authservice.global.response.Response;
import io.ssafy.authservice.member.entity.Member;
import io.ssafy.authservice.member.entity.TokenRedis;
import io.ssafy.authservice.member.respository.MemberRepository;
import io.ssafy.authservice.member.respository.TokenRedisRepository;
import io.ssafy.authservice.oauth2.cookie.CookieUtils;
import io.ssafy.authservice.oauth2.dto.UserResponseDto;
import io.ssafy.authservice.oauth2.enums.Role;
import io.ssafy.authservice.oauth2.jwt.JwtTokenProvider;
import io.ssafy.authservice.repository.MailRedisRepository;
import io.ssafy.authservice.repository.MyCostumeRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static io.ssafy.authservice.global.response.Response.ERROR;
import static io.ssafy.authservice.global.response.Response.OK;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MyCostumeRepository myCostumeRepository;
    private final MailRedisRepository mailRedisRepository;
    private final TokenRedisRepository tokenRedisRepository;

    @Value("${cookie.domain}")
    private static String cookieResponseDomain;
    @Value("${cookie.max-age}")
    private int cookieMaxAge;

    @Override
    public Response<?> joinMember(MemberJoinReqDto memberJoinReqDto) {

        Member nickname = memberRepository.findByNicknameAndIsDeleted(memberJoinReqDto.getNickname(), false)
                .orElse(null);
        if (nickname != null) {
            return ERROR("이미 사용중인 닉네임입니다.", HttpStatus.BAD_REQUEST);
        }

        if (memberJoinReqDto.getEmail().equals("admin@ssafy.com")) {
            String encryptedPassword = passwordEncoder.encode(memberJoinReqDto.getPassword());
            Member member = Member.builder()
                    .id(UUID.randomUUID().toString())
                    .email(memberJoinReqDto.getEmail())
                    .password(encryptedPassword)
                    .nickname(memberJoinReqDto.getNickname())
                    .role(Role.ROLE_ADMIN)
                    .build();

            memberRepository.save(member);
            return OK("관리자 회원가입 완료");
        }
        List<Member> members = memberRepository.findAllByEmailIs(memberJoinReqDto.getEmail());
        boolean flag = true;
        for (Member member : members) {
            if (member.getAuthProvider() == null) {
                flag = false;
                break;
            }
        }

        String encryptedPassword = passwordEncoder.encode(memberJoinReqDto.getPassword());
        if (flag) {
            Member member = Member.builder()
                    .id(UUID.randomUUID().toString())
                    .email(memberJoinReqDto.getEmail())
                    .password(encryptedPassword)
                    .nickname(memberJoinReqDto.getNickname())
                    .isEmailVerified(true)
                    .crystal(20)
                    .level(1)
                    .role(Role.ROLE_USER)
                    .build();
            member = memberRepository.save(member);

            MyCostume myCostume = MyCostume.builder().costumeSeq(1L).member(member).costumeGrade("normal")
                    .costumeName("man_actionhero").costumeImage("man_actionhero").build();

            myCostumeRepository.save(myCostume);
            member.setMyCostume(myCostume, member);
            memberRepository.save(member);

            return OK("정상적으로 회원가입 처리되었습니다.");
        } else {
            return ERROR("이미 가입된 회원이 있습니다.", HttpStatus.BAD_REQUEST);
        }

    }


    @Transactional
    @Override
    public Response<?> loginMember(MemberLoginReqDto memberLoginReqDto, HttpServletResponse response) {

        Member member = memberRepository
                .findByEmailAndAuthProviderAndIsDeleted(memberLoginReqDto.getEmail(), null, false).orElse(null);


        boolean flag = member != null && passwordEncoder.matches(memberLoginReqDto.getPassword(), member.getPassword());

        if (flag) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(member.getId(),
                    memberLoginReqDto.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
            UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

            // 쿠키 생성 및 저장
            saveCookie(response, tokenInfo.getAccessToken());

            // redis에 토큰 저장
            tokenRedisRepository.save(new TokenRedis(authentication.getName(),tokenInfo.getAccessToken(), tokenInfo.getRefreshToken()));


            return OK(null);
        } else {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Response<?> validateNickname(String nickname) {
        Member member = memberRepository.findByNicknameAndIsDeleted(nickname, false).orElse(null);
        MailRedis mail = mailRedisRepository.findByNickname(nickname).orElse(null);
        if (member == null && mail == null) {
            return OK(null);
        } else {
            return ERROR("이미 사용중인 닉네임입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Response<?> validateEmail(String email) {
        Member member = memberRepository.findByEmailAndAuthProviderAndIsDeleted(email, null, false).orElse(null);
        MailRedis mail = mailRedisRepository.findById(email).orElse(null);
        if (member == null && mail == null) {
            return OK(null);
        } else {
            return ERROR("이미 사용중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    protected void saveCookie(HttpServletResponse response, String AccessToken) {
        Cookie cookie = new Cookie("accessToken", AccessToken);
        cookie.setPath("/");
        cookie.setDomain(cookieResponseDomain); // 특정 도메인에서 사용하도록
        cookie.setHttpOnly(true);
        cookie.setMaxAge(cookieMaxAge);
        response.addCookie(cookie);
    }


}
