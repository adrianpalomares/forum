package com.example.forum.users;

import com.example.forum.posts.Post;
import com.example.forum.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;

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

    /**
     * @param userId The id of the user.
     * @return A List of posts from the user. If no user found it will return an empty List.
     */
    @GetMapping("api/users/{id}/posts")
    public List<Post> getPostsFromUser(@PathVariable(value = "id") Long userId) {
        Optional<User> user = userRepository.findById(userId);
        // TODO: Simple handling if empty, will fix
        if (!user.isPresent()) {
            return Collections.emptyList();
        }
        return postService.getPostsByUsername(user.get().getUsername());
    }
}
