package com.example.forum.auth;

import com.example.forum.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("api/auth/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}
