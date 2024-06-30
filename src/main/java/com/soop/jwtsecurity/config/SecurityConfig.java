package com.soop.jwtsecurity.config;

import com.soop.jwtsecurity.handler.CustomSuccessHandler;
import com.soop.jwtsecurity.jwt.JWTFilter;
import com.soop.jwtsecurity.jwt.JWTUtil;
import com.soop.jwtsecurity.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler, JWTUtil jwtUtil) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CORS 설정
        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // 허용할 출처를 설정
                configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE")); // 모든 HTTP 메서드(GET, POST, PUT, DELETE 등)를 허용
                configuration.setAllowCredentials(true); // 자격 증명(쿠키, Authorization 헤더 등)을 포함한 요청을 허용
                configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Content-Type", "Authorization", "X-Requested-With", "Access-Token", "Refresh-Token")); // 모든 헤더를 허용
                configuration.setMaxAge(3600L); // CORS 설정을 브라우저에 캐시할 시간
                configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "Authorization")); // 프론트로 반환할 헤더들 설정
                return configuration;
            }
        }));

        // CSRF 비활성화
        http.csrf((csrf) -> csrf.disable());

        // Form 로그인 비활성화
        http.formLogin((form) -> form.disable());

        // HTTP Basic 인증 비활성화
        http.httpBasic((basic) -> basic.disable());

        // JWT 필터 추가
        http.addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // OAuth2 로그인 설정
        http.oauth2Login((oauth2) -> oauth2
                .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                        .userService(customOAuth2UserService))
                .successHandler(customSuccessHandler)
        );

        // 경로별 인가 설정
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/reissue").permitAll() // 메인 페이지 및 토큰 재발급 경로 접근 허용
                .anyRequest().authenticated() // 기타 모든 요청은 인증 필요
        );

        // 세션 관리 설정: STATELESS
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션을 사용하지 않음

        return http.build();
    }
}
