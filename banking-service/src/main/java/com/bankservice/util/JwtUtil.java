package com.bankservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "very_long_secure_key_for_banking_service_1234567890"; // >= 32 chars
    private final long expirationMs = 1000 * 60 * 60 * 10; // 10 hours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String extractRole(String token) {
        return (String) Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
