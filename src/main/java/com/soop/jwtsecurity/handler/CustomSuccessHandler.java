package com.soop.jwtsecurity.handler;

import com.soop.jwtsecurity.dto.CustomOAuth2User;
import com.soop.jwtsecurity.entityDTO.RefreshEntity;
import com.soop.jwtsecurity.jwt.JWTUtil;
import com.soop.jwtsecurity.mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final UserMapper userMapper;

    public CustomSuccessHandler(JWTUtil jwtUtil, UserMapper userMapper) {
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String access = jwtUtil.createJwt("access", username, role, 600L * 1000); // 10분 (600초)
        String existingRefreshToken = userMapper.searchRefreshEntity(username);

        if (existingRefreshToken != null) {
            userMapper.deleteByRefresh(existingRefreshToken);
        }

        String refresh = jwtUtil.createJwt("refresh", username, role, 86400L *1000); // 24시간 (86400000밀리초)
        addRefreshEntity(username, refresh, 86400L*1000);

        System.out.println("access = " + access);
        System.out.println("refresh = " + refresh);

        // 리프레시 토큰을 HTTP-Only 쿠키로 저장
        createAndAddCookie(response, "refresh", refresh);

        // 액세스 토큰을 쿼리 스트링으로 전달
        response.sendRedirect("http://localhost:3000/login?token=" + access);
    }

    private void addRefreshEntity(String username, String refresh, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());
        userMapper.saveRefreshEntity(refreshEntity);
    }

    private void createAndAddCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60); // 24시간
        cookie.setDomain("localhost");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(false); // localhost 환경에서는 false, 실제 배포 시 true로 설정

        response.addCookie(cookie);

        // SameSite 설정 추가
        response.setHeader("Set-Cookie",
                String.format("%s=%s; Max-Age=%d; Domain=%s; Path=%s; HttpOnly; SameSite=Strict",
                        key, value, 24 * 60 * 60, "localhost", "/"));
    }

}
