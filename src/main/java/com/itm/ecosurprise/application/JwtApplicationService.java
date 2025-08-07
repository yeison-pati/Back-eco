package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.port.in.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtApplicationService implements JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Override
    public String generateToken(String username, String role, int userId) {
        byte[] keyBytes = jwtSecret.getBytes();
        return Jwts.builder()
                .setSubject(username)
                .claim("rol", role)
                .claim("id", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            byte[] keyBytes = jwtSecret.getBytes();
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getRolFromToken(String token) {
        byte[] keyBytes = jwtSecret.getBytes();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("rol", String.class);
    }

    @Override
    public int getIdFromToken(String token) {
        byte[] keyBytes = jwtSecret.getBytes();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Integer.class);
    }

    @Override
    public String renewToken(String oldToken) {
        byte[] keyBytes = jwtSecret.getBytes();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                .build()
                .parseClaimsJws(oldToken)
                .getBody();
        return Jwts.builder()
                .setSubject(claims.getSubject())
                .claim("rol", claims.get("rol"))
                .claim("id", claims.get("id"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS512)
                .compact();
    }
}
