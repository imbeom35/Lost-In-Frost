package io.ssafy.authservice.oauth2.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.lettuce.core.RedisException;
import io.ssafy.authservice.global.config.security.ExpireTime;
import io.ssafy.authservice.global.exception.TokenNotFoundException;
import io.ssafy.authservice.member.entity.TokenRedis;
import io.ssafy.authservice.member.respository.MemberRepository;
import io.ssafy.authservice.member.respository.TokenRedisRepository;
import io.ssafy.authservice.oauth2.cookie.CookieUtils;
import io.ssafy.authservice.oauth2.dto.UserResponseDto;
import io.ssafy.authservice.oauth2.service.CustomUserDetailsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.PartialUpdate;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final String TYPE_ACCESS = "access";
    private static final String TYPE_REFRESH = "refresh";
    @Value("${cookie.domain}")
    private static String cookieResponseDomain;

    @Value("${cookie.max-age}")
    private int cookieMaxAge;

    private final TokenRedisRepository tokenRedisRepository;
    private final RedisKeyValueTemplate redisKeyValueTemplate;
    private final Key key;


    public JwtTokenProvider(@Value("${token.secret}") String secretKey, TokenRedisRepository tokenRedisRepository, RedisKeyValueTemplate redisKeyValueTemplate) {
        this.tokenRedisRepository = tokenRedisRepository;
        this.redisKeyValueTemplate = redisKeyValueTemplate;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // Authentication 을 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public UserResponseDto.TokenInfo generateToken(Authentication authentication) {
        log.debug("유저 이름 : {}", authentication.getName());

        return generateToken(authentication.getName(), authentication.getAuthorities());

    }

    // name, authorities 를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public UserResponseDto.TokenInfo generateToken(String name,
            Collection<? extends GrantedAuthority> inputAuthorities) {

        // 권한 추출
        String authorities = inputAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();

        // AccessToken 생성
        String accessToken = generateAccessToken(name, inputAuthorities);

        // RefreshToken 생성
        String refreshToken = Jwts.builder()
                .claim(AUTHORITIES_KEY, authorities)
                .claim("type", TYPE_REFRESH)
                .setSubject(name)
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ExpireTime.REFRESH_TOKEN__EXPIRE_TIME)) // 토큰 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return UserResponseDto.TokenInfo.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpirationTime(ExpireTime.ACCESS_TOKEN_EXPIRE_TIME)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(ExpireTime.REFRESH_TOKEN__EXPIRE_TIME)
                .build();
    }

    /**
     * 토큰을 이용하여 Authentication 객체를 생성하는 메서드
     * @param token 토큰
     * @param memberId 회원 식별자
     * @return UsernamePasswordAuthenticationToken
     */
    public Authentication getAuthentication(String token, String memberId) throws TokenNotFoundException{
        // 토큰 복호화
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails principal = new User(memberId, "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * 토큰 유효성 검사
     * @param token 토큰
     * @param response 응답
     * @return 유효성 여부
     */
    public String validateToken(String token, HttpServletResponse response) throws IOException {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT Token", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            log.error("만료된 액세스 토큰 사용!!", e);
            return null;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty.", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT claims string is empty.");
        }
        return null;
    }


    /**
     * AccessToken 생성 메소드
     * @param name 회원 식별자
     * @param inputAuthorities 권한
     * @return AccessToken
     */
    public String generateAccessToken(String name,  Collection<? extends GrantedAuthority> inputAuthorities){
        // 권한 가져오기
        String authorities = inputAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();

        return Jwts.builder()
                .setSubject(name)
                .claim(AUTHORITIES_KEY, authorities)
                .claim("type", TYPE_ACCESS)
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ExpireTime.ACCESS_TOKEN_EXPIRE_TIME)) // 토큰 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    /**
     * 토큰을 이용하여 Authentication 객체를 생성하는 메서드
     * @param token 토큰
     * @param memberId 회원 식별자
     * @return UsernamePasswordAuthenticationToken
     */
    public UsernamePasswordAuthenticationToken createAuthenticationFromToken(String token, String memberId) throws TokenNotFoundException{

        Authentication authentication = getAuthentication(token, memberId);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private void saveCookie(HttpServletResponse response, String AccessToken) {
        Cookie cookie = new Cookie("accessToken", AccessToken);
        cookie.setPath("/");
        cookie.setDomain(cookieResponseDomain); // 특정 도메인에서 사용하도록
        cookie.setHttpOnly(true);
        cookie.setMaxAge(cookieMaxAge);
        response.addCookie(cookie);
    }

    public UsernamePasswordAuthenticationToken replaceAccessToken(HttpServletResponse response, String token) throws IOException {
        try{
            // redis 엔티티 조회
            TokenRedis tokenRedis = tokenRedisRepository.findByAccessToken(token).orElseThrow(() -> new TokenNotFoundException("다시 로그인 해 주세요."));
            if (tokenRedis == null) {
                throw new TokenNotFoundException("다시 로그인 해 주세요.");
            }
            String refreshToken = Objects.requireNonNull(tokenRedis).getRefreshToken();
            // 리프레시 토큰 유효성 검사 및 subject 추출
            Claims jwt = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken).getBody();
            String memberId = jwt.getSubject();

            log.error("## 토큰 재발급 시작..");

            // authentication 생성
            UsernamePasswordAuthenticationToken authentication = createAuthenticationFromToken(refreshToken,memberId);
            // 새로운 액세스 토큰 발급
            String newAccessToken = generateAccessToken(memberId, authentication.getAuthorities());


            // 쿠키 AccessToken 업데이트
            saveCookie(response, newAccessToken);

            // redis AccessToken 업데이트
            PartialUpdate<TokenRedis> update = new PartialUpdate<>(memberId, TokenRedis.class)
                    .set("accessToken", newAccessToken);
            redisKeyValueTemplate.update(update);

            log.error("## 토큰 재발급 완료!");

            return authentication;

        }
        // 이미 재 발급된 토큰 사용 or  리프레시 토큰 만료
        catch (ExpiredJwtException | TokenNotFoundException exception) {
            log.error(exception.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "사용 불가능한 토큰입니다. 다시 로그인 후 이용해주세요.");
        } catch (RedisException redisException){
            log.error(redisException.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Redis 서버 에러");
        }
        return null;
    }





}