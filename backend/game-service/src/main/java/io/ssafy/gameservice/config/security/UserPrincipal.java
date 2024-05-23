package io.ssafy.gameservice.config.security;

import io.ssafy.gameservice.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@Slf4j
public class UserPrincipal implements OAuth2User, UserDetails {

    private String id;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Setter
    private Map<String, Object> attributes;

    public UserPrincipal(String id, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.authorities = authorities;

    }

    public static UserPrincipal create(Member member) {
        log.debug("## 일반 로그인시에 권한 생성");
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(member.getRole().name()));
        return new UserPrincipal(
                    member.getId(),
                    member.getPassword(),
                    authorities
        );

    }

    public static UserPrincipal create(Member member, Map<String, Object> attributes) {
        log.info("## SNS 로그인 시에 유저 권한 생성");
        log.info("{}", member.getId());
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(member.getRole().name()));
        UserPrincipal userPrincipal = new UserPrincipal(member.getId(), member.getPassword(), authorities);
        userPrincipal.setAttributes(attributes);
        log.info("=> 생성된 유저 권한 : {}", userPrincipal.getAttributes());
        return userPrincipal;
    }
    @Override
    public String getUsername() {
        return id;
    }
    // 이 부분에서 Username을 email로 설정하는 부분에서 시큐리티의 User를 가져왔을 때, email이 세팅된 부분을 가져오는 것 같은데
    // 왜 위에서는 create로 설정이 안 되는지는 모르겠습니다.......

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return id;
    }
}
