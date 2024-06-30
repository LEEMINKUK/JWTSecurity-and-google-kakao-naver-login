package com.soop.jwtsecurity.controller;

import com.soop.jwtsecurity.entityDTO.RefreshEntity;
import com.soop.jwtsecurity.jwt.JWTUtil;
import com.soop.jwtsecurity.mapper.UserMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;

@Controller
@ResponseBody
public class ReissueController {

    private final JWTUtil jwtUtil;
    private final UserMapper userMapper;

    public ReissueController(JWTUtil jwtUtil, UserMapper userMapper) {
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    @PostMapping("/reissue")
    public void reissue(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Reissue endpoint called with cookies: ");
        for (Cookie cookie : request.getCookies()) {
            System.out.println(cookie.getName() + ": " + cookie.getValue());
        }
        // 쿠키에서 리프레시 토큰을 가져옵니다.
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh")) {
                    refresh = cookie.getValue();
                    break;
                }
            }
        }

        if (refresh == null) {
            response.sendRedirect("http://localhost:3000/login?error=refresh token null");
            return;
        }

        // 토큰 만료 확인
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            response.sendRedirect("http://localhost:3000/login?error=refresh token expired");
            return;
        }

        // 리프레시 토큰인지 확인
        String category = jwtUtil.getCategory(refresh);
        if (!"refresh".equals(category)) {
            response.sendRedirect("http://localhost:3000/login?error=invalid refresh token");
            return;
        }

        // DB에 리프레시 토큰이 존재하는지 확인
        Boolean isExist = userMapper.existsByRefresh(refresh);
        if (!isExist) {
            response.sendRedirect("http://localhost:3000/login?error=invalid refresh token");
            return;
        }

        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        // 새로운 액세스 토큰 발급
        String newAccess = jwtUtil.createJwt("access", username, role, 600L * 1000);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400L * 1000);

        // DB에 리프레시 토큰 업데이트
        String existingRefreshToken = userMapper.searchRefreshEntity(username);
        if (existingRefreshToken != null) {
            userMapper.deleteByRefresh(existingRefreshToken);
        }
        addRefreshEntity(username, newRefresh, 86400L * 1000);

        // 새 리프레시 토큰을 HTTP-Only 쿠키에 추가
        createAndAddCookie(response, "refresh", newRefresh);

        // 새 액세스 토큰을 쿼리 스트링으로 전송
        response.setHeader("Authorization", "Bearer " + newAccess);
        response.setStatus(HttpStatus.OK.value());

        System.out.println("newRefresh = " + newRefresh);
        System.out.println("newAccess = " + newAccess);
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
