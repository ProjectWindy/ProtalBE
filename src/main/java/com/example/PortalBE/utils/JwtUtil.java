package com.example.PortalBE.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    public String createJwt(String email) {
        Date now = new Date();
        Date expiration = new Date(
                now.getTime() + jwtExpirationMs
        );
        String jwtToken = Jwts.builder()
                .setSubject(email) // chu so huu
                .setIssuedAt(new Date()) // ngay tao
                .setExpiration(expiration) // thoi gian het han
                .signWith(getSigningKey()) // chu ky jwt
                .compact();
        return jwtToken;
    }
    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            // Log a warning or error
            throw new IllegalArgumentException("JWT secret key is too short. It must be at least 256 bits.");
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean validateJwt(String token) {
        try {
            // validate token thi se so sanh chu ky (scret) va thoi gian song cua token
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parse(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
