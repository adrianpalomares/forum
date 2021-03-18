package com.example.forum.posts;

import com.example.forum.likes.Like;
import com.example.forum.likes.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//TODO: Create a ResponseObject for post

@RestController
//@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    @CrossOrigin(origins = "*")
    @GetMapping("api/posts/{id}")
    public Optional<Post> getPostById(@PathVariable(value = "id") Long postId) {
        return postService.getPostById(postId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("api/posts")
    public Iterable<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * Grab the likes from a post.
     *
     * @return A List of likes.
     */
    @CrossOrigin(origins = "*")
    @GetMapping("api/posts/{id}/likes")
    public List<Like> getLikesFromPost(@PathVariable(value = "id") Long postId) {
        // Grab likes using post service
        List<Like> listOfLikes = likeService.getLikesByPost(postId);
        return listOfLikes;
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
