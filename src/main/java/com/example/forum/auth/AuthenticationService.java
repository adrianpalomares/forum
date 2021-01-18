package com.example.forum.auth;

import com.example.forum.users.User;
import com.example.forum.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepository; // supposed to be service

    @Autowired
    JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        user.orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist."));
        // Simple password match check
        if (user.get().getPassword().equals(loginRequest.getPassword())) {
            return new LoginResponse(jwtUtil.generateToken(user.get().getUsername()), user.get().getUsername());

        }
        return null;
    }

    // TODO: Throw exception when user exists
    // TODO: Make username/ email unique
    public RegisterResponse register(RegisterRequest registerRequest) {
        // Get user if it exists return error code
        Optional<User> user = userRepository.findByUsername(registerRequest.getUsername());
        System.out.println(user.isPresent());
        if (!user.isPresent()) {
            // Create new user
            User newUser = new User();
            newUser.setUsername(registerRequest.getUsername());
            newUser.setPassword(registerRequest.getPassword());
            newUser.setEmail(registerRequest.getEmail());
            newUser.setCreated(Instant.now());
            User savedUser = userRepository.save(newUser);

            return new RegisterResponse(jwtUtil.generateToken(savedUser.getUsername()));
        }
        return new RegisterResponse(null);
    }
}
