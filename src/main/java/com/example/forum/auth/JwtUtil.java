package com.example.forum.auth;

import com.example.forum.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {
    private String SECRET;

    public JwtUtil(@Value("${secretkey}") String SECRET) {
        this.SECRET = SECRET;
    }

    public String generateToken(String username) {
        String token = Jwts.builder().setId("forumapplication")
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000000))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
        return token;
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        return username;
    }

    public Date extractExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
        Date expiration = claims.getExpiration();
        return expiration;
    }

    public Boolean isExpired(String token) {
        return extractExpiration(token).before(new Date());

    }

    public Boolean validateToken(String token, User user) {
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isExpired(token);
    }
}
