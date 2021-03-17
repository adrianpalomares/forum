package com.example.forum.likes;

import com.example.forum.posts.Post;
import com.example.forum.posts.PostRepository;
import com.example.forum.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    @Autowired
    PostRepository postRepository;

    public List<Like> getLikesByPost(long postId) {
        // Find post
        // TODO: Handle this optional
        Optional<Post> post = postRepository.findById(postId);
        List<Like> likes = likeRepository.findByPost(post.get());
        return likes;
    }

//    public Like createNewLike(long userId, long postId, boolean value) {
//        // Grab User and Post objects
////        Like newLike = new Like(user, post, value);
//        // Save to database
////        Like savedLike = likeRepository.save(newLike);
////        return savedLike;
//    }
}
