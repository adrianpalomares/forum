package com.example.forum.posts;

import com.example.forum.users.User;
import com.example.forum.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(PostRequest postRequest) {
        Optional<User> user = userRepository.findById((postRequest.getUserId()));
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setUser(user.get());
        post.setText(postRequest.getText());
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, PostRequest postRequest) {
        Optional<Post> post = postRepository.findById(postId);
        post.get().setTitle(postRequest.getTitle());
        if (postRequest.getUserId() != null) {
            Optional<User> user = userRepository.findById(postRequest.getUserId());
            post.get().setUser(user.get());
        }
        return postRepository.save(post.get());
    }

    public Post deletePost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        postRepository.delete(post.get());
        return post.get();
    }

    // TODO: handle if user is not found
    public List<Post> getPostsByUsername(String username) {
        // Grab the user object
        Optional<User> user = userRepository.findByUsername(username);
        List<Post> posts = postRepository.findByUser(user.get());
        return posts;
    }

}
