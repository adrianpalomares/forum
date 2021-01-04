package com.example.forum.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("api/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("api/users/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId);
    }

    @PostMapping("api/users")
    public User createUser(@RequestBody User user) {
        user.setCreated(Instant.now());
        return userRepository.save(user);
    }

    @PutMapping("api/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails) {
        Optional<User> user = userRepository.findById(userId);
        user.get().setEmail(userDetails.getEmail());
        user.get().setUsername((userDetails.getUsername()));

        return userRepository.save(user.get());
    }
}
