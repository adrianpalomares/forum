package com.example.forum.likes;

import com.example.forum.posts.Post;
import com.example.forum.posts.PostRepository;
import com.example.forum.users.User;
import com.example.forum.users.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    public List<Like> getLikesByPost(long postId) {
        // Find post
        // TODO: Handle this optional
        Optional<Post> post = postRepository.findById(postId);
        List<Like> likes = likeRepository.findByPost(post.get());
        return likes;
    }

    /**
     * Will handle the submission of likes. If has already submitted a like for a post,
     * then another like should not be submitted.
     *
     * @param userId
     * @param postId
     * @param value
     * @return The Like object that was either created or retrieved.
     */
    // TODO: Handle if the value changes. eg. From like to dislike. At the moment, if the like exists it will just return
    // it, it won't check if the value changes.
    public Like submitLike(long userId, long postId, boolean value) {
        // This is the value that will be returned in the end
        Like result;
        // Grab User and Post objects
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);

        // Grab the Like, if it exists
        Like queryResult = likeRepository.findByPostAndUser(post.get(), user.get());

        // If the like does not exist, then create it.
        if (queryResult == null) {
            Like createdLike = new Like(user.get(), post.get(), value);
            result = likeRepository.save(createdLike);
        } else {
            // Else, check the value field. If it did not change just send back the like. Else, update.
            if (queryResult.getValue() != value) {
                // Update and save
                queryResult.setValue(value);
                Like savedLike = likeRepository.save(queryResult);
                return savedLike;
            }
            result = queryResult;
        }

        return result;
    }
}
