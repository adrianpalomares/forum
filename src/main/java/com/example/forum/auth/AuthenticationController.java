package com.example.forum.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("api/auth/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("api/auth/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = authenticationService.register(registerRequest);
        ResponseEntity<RegisterResponse> response = new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }
}
