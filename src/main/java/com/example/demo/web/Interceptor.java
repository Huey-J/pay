package com.example.demo.web;

import com.example.demo.web.service.JwtTokenProvider;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        if (true) return true;

        // 토큰 검사
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = request.getHeader("Authorization");

        if (token == null) {        // 토큰 없음
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\":\"Unauthorized(NO TOKEN)\"}");
            response.setStatus(401);
            return false;
        }

        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {     // Bearer 제거
            token = token.substring(7);
        }
        try {
            jwtTokenProvider.validateToken(token);
        } catch (Exception e) {         // 유효하지 않은 토큰
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\":\"Unauthorized(INVALID TOKEN)\"}");
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
