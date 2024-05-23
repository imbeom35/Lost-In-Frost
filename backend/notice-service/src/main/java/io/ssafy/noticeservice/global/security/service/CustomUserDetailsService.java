package io.ssafy.noticeservice.global.security.service;

import io.ssafy.noticeservice.entity.Member;
import io.ssafy.noticeservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        log.debug("유저 권한 생성 1");
        log.debug("유저 권한 생성 멤버 식별자 : {}", memberId);

        return memberRepository.findById(memberId)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
    }


    private UserDetails createUserDetails (Member member) {
        log.debug("유저 정책 생성! : {}", member.getId());
        if (member.getAuthProvider() != null) {
            return new User (member.getId(), "", member.getAuthorities());
        } else {
            return new User (member.getId(), member.getPassword(), member.getAuthorities());
        }


    }


}
