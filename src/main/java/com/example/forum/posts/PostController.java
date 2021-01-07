package com.example.forum.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//TODO: Create a ResponseObject for post

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/posts/{id}")
    public Optional<Post> getPostById(@PathVariable(value = "id") Long postId) {
        return postService.getPostById(postId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/posts")
    public Iterable<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("api/posts")
    public Post createPost(@RequestBody PostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @PutMapping("api/posts/{id}")
    public Post updatePost(@PathVariable(value = "id") Long postId, @RequestBody PostRequest postRequest) {

        return postService.updatePost(postId, postRequest);

    }

    @DeleteMapping("api/posts/{id}")
    public Post deletePost(@PathVariable(value = "id") Long postId) {
        return postService.deletePost(postId);
    }

}
