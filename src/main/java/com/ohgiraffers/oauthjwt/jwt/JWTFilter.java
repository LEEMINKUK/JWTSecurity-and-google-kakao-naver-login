package com.ohgiraffers.oauthjwt.jwt;

import com.ohgiraffers.oauthjwt.dto.CustomOAuth2User;
import com.ohgiraffers.oauthjwt.dto.UserDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 access키에 담긴 토큰을 꺼냄
        String accessToken = request.getHeader("access");

        // 토큰이 없다면 다음 필터로 넘김
        if (accessToken == null) {

            filterChain.doFilter(request, response);

            return;
        }

        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("access 토큰 만료");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 토큰이 access인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("access 토큰이 아닙니다.");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

//        //cookie들을 불러온 뒤 Authorization Key에 담긴 쿠키를 찾음
//        String authorization = null;
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//
//            System.out.println(cookie.getName());
//            if (cookie.getName().equals("Authorization")) {
//
//                authorization = cookie.getValue();
//            }
//        }
//
//        //Authorization 헤더 검증
//        if (authorization == null) {
//
//            System.out.println("token null");
//            filterChain.doFilter(request, response);
//
//            //조건이 해당되면 메소드 종료 (필수)
//            return;
//        }
//
//        //토큰
//        String token = authorization;
//
//        //토큰 소멸 시간 검증
//        if (jwtUtil.isExpired(token)) {
//
//            System.out.println("token expired");
//            filterChain.doFilter(request, response);
//
//            //조건이 해당되면 메소드 종료 (필수)
//            return;
//        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        //userDTO를 생성하여 값 set
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setRole(role);

        //CustomOAuth2User 회원 정보 객체 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);




//        // 헤더에서 access키에 담긴 토큰을 꺼냄
//        String accessToken = request.getHeader("access");
//
//        // 토큰이 없다면 다음 필터로 넘김
//        if (accessToken == null) {
//
//            filterChain.doFilter(request, response);
//
//            return;
//        }
//
//        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
//        try {
//            jwtUtil.isExpired(accessToken);
//        } catch (ExpiredJwtException e) {
//
//            //response body
//            PrintWriter writer = response.getWriter();
//            writer.print("access 토큰 만료");
//
//            //response status code
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        // 토큰이 access인지 확인 (발급시 페이로드에 명시)
//        String category = jwtUtil.getCategory(accessToken);
//
//        if (!category.equals("access")) {
//
//            //response body
//            PrintWriter writer = response.getWriter();
//            writer.print("access 토큰이 아닙니다.");
//
//            //response status code
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        // username, role 값을 획득
//        String username = jwtUtil.getUsername(accessToken);
//        String role = jwtUtil.getRole(accessToken);
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername(username);
//        userEntity.setRole(role);
//        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
//
//        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, CustomOAuth2User.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        filterChain.doFilter(request, response);
    }
}