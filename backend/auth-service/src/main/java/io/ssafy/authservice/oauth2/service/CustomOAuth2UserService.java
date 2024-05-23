package io.ssafy.authservice.oauth2.service;


import io.ssafy.authservice.entity.MyCostume;
import io.ssafy.authservice.member.entity.Member;
import io.ssafy.authservice.member.respository.MemberRepository;
import io.ssafy.authservice.oauth2.UserPrincipal;
import io.ssafy.authservice.oauth2.enums.AuthProvider;
import io.ssafy.authservice.oauth2.enums.Role;
import io.ssafy.authservice.oauth2.userinfo.OAuth2UserInfo;
import io.ssafy.authservice.oauth2.userinfo.OAuth2UserInfoFactory;
import io.ssafy.authservice.repository.MyCostumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final MyCostumeRepository myCostumeRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        log.debug("loaduser =>");
        log.debug(" {}", oAuth2UserRequest);
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        log.debug(" {}", oAuth2UserService);
        OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);
        log.debug(" {}", oAuth2User);

        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    protected OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        log.debug("processOAuth2User =>");
        log.debug(" {}", oAuth2UserRequest);
        log.debug(" {}", oAuth2User.getName());
        //OAuth2 로그인 플랫폼 구분
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());
        log.debug("로그인 플랫폼 : {}", authProvider);
        log.debug("유저 정보 : {}", oAuth2UserInfo.getAttributes());

        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new RuntimeException("Email not found from OAuth2 provider");
        }

        Member member = memberRepository.findById(oAuth2UserInfo.getOAuth2Id()).orElse(null);
        //이미 가입된 경우
        if (member != null) {
            log.warn("이미 가입된 경우!!");
            member = updateUser(member, oAuth2UserInfo);
        }
        //가입되지 않은 경우
        else {
            member = registerUser(authProvider, oAuth2UserInfo);
        }
        log.debug("멤버2 : {}", member.getId());
        log.debug("멤버2 : {}", member.getId());
        log.debug("멤버2 : {}", member.getId());

        return UserPrincipal.create(member, oAuth2UserInfo.getAttributes());
    }

    private Member registerUser(AuthProvider authProvider, OAuth2UserInfo oAuth2UserInfo) {
        log.debug("{}", oAuth2UserInfo.getAttributes());
        Member tmp = memberRepository.findByNicknameAndIsDeleted(oAuth2UserInfo.getNickname(), false).orElse(null);
        String nickname = oAuth2UserInfo.getNickname();
        if (tmp != null) {
            nickname = UUID.randomUUID().toString();
        }

        Member member = Member.builder()
                .email(oAuth2UserInfo.getEmail())
                .password(null)
                .level(1)
                .crystal(20)
                .isEmailVerified(true)
                .nickname(nickname)
                .id(oAuth2UserInfo.getOAuth2Id())
                .authProvider(authProvider)
                .role(Role.ROLE_USER)
                .build();
        member = memberRepository.save(member);

        MyCostume myCostume = myCostumeRepository.save(
                MyCostume.builder().
                        costumeSeq(1L).
                        member(member).
                        costumeGrade("normal").
                        costumeName("man_actionhero").
                        costumeImage("man_actionhero").
                        build());
        member.setMyCostume(myCostume, member);
        memberRepository.save(member);
        log.debug("registerUser => {}", member.getId());
        return member;
    }

    private Member updateUser(Member member, OAuth2UserInfo oAuth2UserInfo) {
        log.debug("updateUser =>");
        log.debug(" {}", member);
        log.debug(" {}", oAuth2UserInfo);
        return member.update(oAuth2UserInfo);
    }
}
