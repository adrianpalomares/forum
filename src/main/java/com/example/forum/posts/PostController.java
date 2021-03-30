package com.example.forum.posts;

import com.example.forum.comments.Comment;
import com.example.forum.comments.CommentDto;
import com.example.forum.comments.CommentService;
import com.example.forum.likes.Like;
import com.example.forum.likes.LikeDto;
import com.example.forum.likes.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//TODO: Create a ResponseObject for post

@RestController
//@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    @Autowired
    CommentService commentService;

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
    public List<LikeDto> getLikesFromPost(@PathVariable(value = "id") Long postId) {
        // Grab likes using like service
        List<Like> listOfLikes = likeService.getLikesByPost(postId);

        // Converting into a list of LikeDto's
        List<LikeDto> response = listOfLikes.stream()
                .map((like) -> new LikeDto(like.getId(), like.getUser().getId(), like.getValue()))
                .collect(Collectors.toList());

        return response;
    }

    /**
     * Grab comments from a post.
     *
     * @param postId
     * @return List of comments
     */
    @GetMapping("api/posts/{id}/comments")
    public List<CommentDto> getCommentsFromPost(@PathVariable(value = "id") Long postId) {
        // Grab comments using comment service
        List<Comment> listOfComments = commentService.getCommentsByPost(postId);

        // Convert into a list of CommentDto's
        List<CommentDto> response = listOfComments.stream()
                .map((comment) -> new CommentDto(comment.getId(), comment.getUser().getId(), postId, comment.getContent()))
                .collect(Collectors.toList());

        return response;
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

