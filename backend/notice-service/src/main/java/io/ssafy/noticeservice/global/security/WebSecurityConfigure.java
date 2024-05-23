package io.ssafy.noticeservice.global.security;

import io.ssafy.noticeservice.global.security.jwt.JwtAuthenticationFilter;
import io.ssafy.noticeservice.global.security.jwt.JwtTokenProvider;
import io.ssafy.noticeservice.global.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@Slf4j
public class WebSecurityConfigure {

        private final JwtTokenProvider jwtTokenProvider;
        private final CustomUserDetailsService customUserDetailsService;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                log.debug("filterChain => ");
                log.debug("   {}", http.toString());

                // 기타 보안 설정
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .formLogin(AbstractHttpConfigurer::disable)
                                .rememberMe(AbstractHttpConfigurer::disable)
                                .httpBasic(AbstractHttpConfigurer::disable)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                // 요청에 대한 권한 설정
                http.authorizeHttpRequests(auth -> auth
                                .requestMatchers(request -> request
                                                .getHeader("X-Gateway-token").equals("c101"))
                                .permitAll()
                                .anyRequest().denyAll());
                // jwt filter 설정
                http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, customUserDetailsService),
                                UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
