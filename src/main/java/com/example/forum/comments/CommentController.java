package com.example.forum.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("api/comments")
    public Iterable<Comment> getAllComments(@RequestParam("postId") Optional<Long> postId) {
        if (postId.isPresent()) {
            return commentService.getCommentsByPost(postId.get());
        } else {
            return commentService.getAllComments();
        }
    }

    @PostMapping("api/comments")
    public Comment createComment(@RequestBody CommentRequestObject commentRequestObject) {
        return commentService.createComment(commentRequestObject);
    }
}
