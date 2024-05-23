package io.ssafy.authservice.oauth2.handler;

import io.ssafy.authservice.member.entity.TokenRedis;
import io.ssafy.authservice.member.respository.TokenRedisRepository;
import io.ssafy.authservice.oauth2.cookie.CookieAuthorizationRequestRepository;
import io.ssafy.authservice.oauth2.cookie.CookieUtils;
import io.ssafy.authservice.oauth2.dto.UserResponseDto;
import io.ssafy.authservice.oauth2.jwt.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${spring.security.oauth2.authorized-redirect-uris}")
    private String redirectUri;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final TokenRedisRepository tokenRedisRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.debug("성공적으로 인증 수행 완료!!!!!!");
        log.debug("{}", authentication.getName());
        String targetUrl = determineTargetUrl(request, response, authentication);
        log.debug("{}", targetUrl);

        if (response.isCommitted()) {
            log.debug("Response has already been committed.");
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.debug("{}", request.getRequestURI());
        log.debug("{}", response);
        log.debug("{}", authentication.getPrincipal().toString());
        Optional<String> redirectUri = CookieUtils.getCookie(request, CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        log.debug("{}", redirectUri);
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new RuntimeException("redirect URIs are not matched.");
        }
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        // JWT 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 쿠키 생성 및 저장
        Cookie cookie = new Cookie("accessToken", tokenInfo.getAccessToken());
        cookie.setPath("/");
        cookie.setDomain(String.valueOf(redirectUri)); // 특정 도메인에서 사용하도록
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 30);
        response.addCookie(cookie);

        // redis에 토큰 저장
        tokenRedisRepository.save(new TokenRedis(authentication.getName(), tokenInfo.getAccessToken(), tokenInfo.getRefreshToken()));

        return UriComponentsBuilder.
                fromUriString(targetUrl)
                .build().toUriString();
    }

    // 권한 정보 삭제
    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    // uri 검증
    private boolean isAuthorizedRedirectUri(String uri) {
        log.debug("{}", uri);
        URI clientRedirectUri = URI.create(uri);
        log.debug("{}",redirectUri);
        URI authorizedUri = URI.create(redirectUri);
        log.debug("{}", authorizedUri);

        if (authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort()) {
            return true;
        }
        return false;
    }
}
