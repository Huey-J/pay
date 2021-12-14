package com.example.demo.web.service;

import com.example.demo.model.user.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {

    private String secretKey = "Top_Secret";
    private long tokenValidMilisecond = 1000L * 60 * 60;    // 1시간만 토큰 유효

    // Jwt 토큰 생성
    public String createToken(User user) {
        Date now = new Date();

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("user_id", user.getId());
        payloads.put("email", user.getEmail());

        return Jwts.builder()
                .setClaims(payloads)        // 데이터 (body)
                .setIssuedAt(now)           // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + tokenValidMilisecond))  // 유효시간
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())       // 암호화 알고리즘, 키 세팅
                .compact();
    }

    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserIdFromJwt(String jwt) {
        return getClaims(jwt).getBody().get("user_id").toString();
    }
    // Jwt 토큰에서 회원 구별 정보 추출
    public String getEmailFromJwt(String jwt) {
        return getClaims(jwt).getBody().get("email").toString();
    }

    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    //JWT복호화
    private Jws<Claims> getClaims(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey.getBytes(Charset.forName("UTF-8")))
                    .parseClaimsJws(jwt);
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e);
            throw e;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e);
            throw e;
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token: " + e);
            throw e;
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token: " + e);
            throw e;
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e);
            throw e;
        }
    }

}
