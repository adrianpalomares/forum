package com.example.forum.auth;

import com.example.forum.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

public class JwtUtilTest {

    @Test
    public void generateTokenTest() {
        JwtUtil jwtUtil = new JwtUtil("secret");
        String token = jwtUtil.generateToken("sampleusername");
        // Parsing the jwt
        Claims claims = Jwts.parser()
                .setSigningKey("secret".getBytes())
                .parseClaimsJws(token)
                .getBody();
        String tokenId = claims.getId();
        // Making sure it has proper content
        assertThat(tokenId).isEqualTo("forumapplication");
    }

    @Test
    public void extractUsernameTest() {
        JwtUtil jwtUtil = new JwtUtil("secret");
        String token = jwtUtil.generateToken("newuser");
        String result = jwtUtil.extractUsername(token);
        assertThat(result).isEqualTo("newuser");
    }

    @Test
    public void extractExpirationTest() {
        JwtUtil jwtUtil = new JwtUtil("secret");
        String token = jwtUtil.generateToken("testuser");
        Date result = jwtUtil.extractExpiration(token);
        assertThat(result != null);
        assertThat(result instanceof Date);
        assertThat(result.after(new Date()));
    }

    @Test
    public void isExpiredTest() {
        JwtUtil jwtUtil = new JwtUtil("secret");
        String token = jwtUtil.generateToken("sampleuser");
        assertThat(jwtUtil.isExpired(token)).isFalse();
    }

    @Test
    public void isExpiredShouldBeTrueTest() throws InterruptedException {
        JwtUtil jwtUtil = new JwtUtil("secret");

        String token = Jwts.builder()
                .setId("forumapplication")
                .setSubject("testuser").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1))
                .signWith(SignatureAlgorithm.HS256, "secret".getBytes())
                .compact();

        // Waiting for token to expire
        Thread.sleep(10);
        assertThatThrownBy(() -> {
            Boolean result = jwtUtil.isExpired(token);
        }).isInstanceOf(ExpiredJwtException.class);

    }

    @Test
    public void validateShouldBeTrue() {
        JwtUtil jwtUtil = new JwtUtil("secret");
        User user = new User(1, "user132", "password", "user132@email.com", Instant.now());
        String token = jwtUtil.generateToken(user.getUsername());
        Boolean result = jwtUtil.validateToken(token, user);
        assertThat(result).isTrue();
    }

    @Test
    public void validateShouldBeFalse() {
        JwtUtil jwtUtil = new JwtUtil("secret");
        User user = new User(1, "user132", "password", "user132@email.com", Instant.now());
        User user2 = new User(1, "user5", "password", "user132@email.com", Instant.now());
        String token = jwtUtil.generateToken(user.getUsername());
        Boolean result = jwtUtil.validateToken(token, user2);
        assertThat(result).isFalse();
    }
}
