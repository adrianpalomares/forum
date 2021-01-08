package com.example.forum.auth;

import com.example.forum.users.User;
import com.example.forum.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepository; // supposed to be service

    @Autowired
    JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        user.orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist."));
        return new LoginResponse(jwtUtil.generateToken(user.get().getUsername()));
    }
}
