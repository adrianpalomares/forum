package com.example.forum.auth;

import com.example.forum.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepository; // supposed to be service

    @Autowired
    JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
//        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(loginRequest.getUsername()));
        // Check if input password matches

        // If it does generate token

        // Else maybe a message
        return new LoginResponse(jwtUtil.generateToken("adrian"));
    }
}
