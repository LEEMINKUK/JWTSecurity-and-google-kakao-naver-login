package com.soop.jwtsecurity.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // 리프레시 토큰 쿠키 제거
        Cookie refreshCookie = new Cookie("refresh", null);
        refreshCookie.setMaxAge(0); // 쿠키를 즉시 만료
        refreshCookie.setHttpOnly(true);
        refreshCookie.setPath("/");
        response.addCookie(refreshCookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
