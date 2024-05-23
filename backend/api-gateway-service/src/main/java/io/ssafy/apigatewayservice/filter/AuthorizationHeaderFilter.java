package io.ssafy.apigatewayservice.filter;

import o.js

import lombok.RequiredArgsCoimport lombok.extern.slf4j.Slf4j;
import org.springframeworimport rg.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@Component
public lass
    @Value("${token.secret}")
    private String jwtSecret;


    /**
     * 설정 관련 클래스 캐스팅
     */
    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    /**
     * 필터 적용 메소드
     * @param config 설정 관련 클래스
     * @return GatewayFilter
     */
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 헤더에 Authorization이 없을 경우
            if (!request.getHeaders().containsKey("Authorization")) {
                return onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
            }

            // header 가져 오기
            String authorizationHeader = request.getHeaders().get("Authorization").get(0);
            // jwt 가져 오기
            String jwt = authorizationHeader.replace("Bearer", "");

            switch (isJwtValid(jwt)){
                case 0:
                    break;
                case 1:
                    return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
                case 2:
                    return onError(exchange, "JWT token is expired", HttpStatus.UNAUTHORIZED);
                case 3:
                    return onError(exchange, "Unsupported JWT Token", HttpStatus.UNAUTHORIZED);
                case 4:
                    return onError(exchange, "JWT claims string is empty.", HttpStatus.UNAUTHORIZED);

            }


            return chain.filter(exchange);
        });
    }

    /**
     * JWT 토큰이 유효한지 확인하는 메소드
     * 0 : 유효, 1 : 유효기간 만료, 2 :
     * @param jwt 토큰
     * @return boolean 토큰 유효 여부
     */
    private int isJwtValid(String jwt) {

        String subject = null;
        // TODO: 추후 이 subject가 사용자가 넣었던 ID와 동일한 지 확인 하는 로직을 넣어도 좋을듯 함, redis에 refresh token을 저장하고 이를 통해 subject를 비교해봐도 좋을 듯 함
        try {
            Jws<Claims> jwts = Jwts.parserBuilder().setSigningKey(jwtSecret).build()
                    .parseClaimsJws(jwt);

            subject = jwts.getBody().getSubject();
            if (subject == null || subject.isEmpty()) {
                return 1;
            }
            Date date = new Date();
            if (jwts.getBody().getExpiration().getTime() - date.getTime() < 3600000) {
                return 2;
            }
            return 0;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
            return 1;
        } catch (ExpiredJwtException e) {
       

            return 2;
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            return 3;
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
            return 4;
        }

    }

    /**
     * 에러 발생 시 처리하는 메소드
     * @param exchange http 요청/응답 관련 메소드
     * @param err 에러 메시지
     * @param httpStatus 에러 상태 코드
     * @return Mono<Void>
     */
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        log.error(err);
        return response.setComplete();
        
    }

    /**
     * Config 클래스
     */
    publi statc class Config {
