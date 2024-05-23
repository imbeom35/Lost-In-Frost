//package io.ssafy.apigatewayservice.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//
//@Slf4j
//@Component
//public class CorsFilter extends AbstractGatewayFilterFactory<CorsFilter.Config> {
//    @Override
//    public GatewayFilter apply(CorsFilter.Config config) {
//        return null;
//    }
//
//    public class Config {
//    }
//
////    @Bean
////    public WebFilter corsFilter() {
////        return (ServerWebExchange ctx, WebFilterChain chain) -> {
////            ServerHttpRequest request = ctx.getRequest();
////            if (isCorsRequired(request)) {
////                ServerHttpResponse response = ctx.getResponse();
////                HttpHeaders headers = response.getHeaders();
////                headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
////                headers.add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
////                headers.add("Access-Control-Max-Age", "3600");
////                headers.add("Access-Control-Allow-Headers", "Authorization, Content-Type");
////                headers.add("Access-Control-Allow-Credentials", "true");
////            }
////            return chain.filter(ctx);
////        };
////    }
//}
