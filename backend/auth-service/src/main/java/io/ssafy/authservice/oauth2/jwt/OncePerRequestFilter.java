package io.ssafy.authservice.oauth2.jwt;

import io.ssafy.authservice.oauth2.cookie.CookieUtils;
import io.ssafy.authservice.oauth2.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class OncePerRequestFilter extends org.springframework.web.filter.OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("## doFilterInternal 동작!!");

        // 쿠키에서 토큰 추출
        Optional<Cookie> accessToken = CookieUtils.getCookie(request,"accessToken" );
        if (accessToken.isPresent()) {
            String validToken = jwtTokenProvider.validateToken(String.valueOf(accessToken.get().getValue()), response);
            UsernamePasswordAuthenticationToken authentication;
            if (validToken != null) {
                authentication = jwtTokenProvider.createAuthenticationFromToken(accessToken.get().getValue(), validToken);
            }
            // 토큰이 만료되었을 경우 -> 새로운 토큰 발급
            else {
                authentication = jwtTokenProvider.replaceAccessToken(response, accessToken.get().getValue());
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
