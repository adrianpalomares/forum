package com.example.forum.likes;

import com.example.forum.posts.Post;
import com.example.forum.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    // Grab likes by post
    List<Like> findByPost(Post post);

    // Query for a like by Post and User
    Like findByPostAndUser(Post post, User user);
}
