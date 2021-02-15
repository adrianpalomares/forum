package com.example.forum.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class CommentController {
    @Value("${client.url}")
    private String clientUrl;

    @Autowired
    CommentService commentService;

    @CrossOrigin(origins = "*")
    @GetMapping("api/comments")
    public Iterable<Comment> getAllComments(@RequestParam("postId") Optional<Long> postId) {
        if (postId.isPresent()) {
            return commentService.getCommentsByPost(postId.get());
        } else {
            return commentService.getAllComments();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("api/comments")
    public Comment createComment(@RequestBody CommentRequestObject commentRequestObject) {
        return commentService.createComment(commentRequestObject);
    }
}
