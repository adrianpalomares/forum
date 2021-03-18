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
        // Grab likes using like service
        List<Like> listOfLikes = likeService.getLikesByPost(postId);
        return listOfLikes;
    }

    @PostMapping("api/posts")
    public Post createPost(@RequestBody PostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @PostMapping("api/posts/{id}/likes")
    public LikeDto createLike(@PathVariable(value = "id") Long postId, @RequestBody LikeDto likeDto) {
        // Create like using like service
        Like createdLike = likeService.submitLike(likeDto.getUserId(), postId, likeDto.getValue());
        LikeDto response = new LikeDto(createdLike.getId(), createdLike.getUser().getId(), createdLike.getValue());
        return response;
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

// The JSON format that the createLike() will receive in the body request.
class LikeDto {
    private long id;
    private long userId;
    private boolean value;

    public LikeDto(long id, long userId, boolean value) {
        this.id = id;
        this.userId = userId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
