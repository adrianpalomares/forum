package com.example.forum.comments;

import com.example.forum.posts.Post;
import com.example.forum.posts.PostRepository;
import com.example.forum.users.User;
import com.example.forum.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getCommentsByPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        return commentRepository.findByPost(post.get());
    }

    public Comment createComment(CommentRequestObject commentRequestObject) {
        Comment comment = new Comment();
        Optional<Post> post =  postRepository.findById(commentRequestObject.getPostId());
        Optional<User> user = userRepository.findById(commentRequestObject.getUserId());

        comment.setContent(commentRequestObject.getContent());
        comment.setPost(post.get());
        comment.setUser(user.get());
        return commentRepository.save(comment);
    }

}
