package com.example.forum.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class PostController {
    @Autowired
    PostRepository postRepository;

    @GetMapping("api/posts/{id}")
    public Optional<Post> getPostById(@PathVariable(value = "id") Long postId) {
        return postRepository.findById(postId);
    }

    @GetMapping("api/posts")
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping("api/posts")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("api/posts/{id}")
    public Post updatePost(@PathVariable(value = "id") Long postId, @RequestBody Post postDetails) {
        Optional<Post> post = postRepository.findById(postId);
        // Handle if null
        post.get().setTitle(postDetails.getTitle());
        Post updatedPost = postRepository.save(post.get());
        return updatedPost;

    }

    @DeleteMapping("api/posts/{id}")
    public Post deletePost(@PathVariable(value = "id") Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        postRepository.delete(post.get());
        return post.get();
    }

}
