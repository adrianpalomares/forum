package com.example.forum.likes;

import com.example.forum.posts.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    // Grab likes by post
    List<Like> findByPost(Post post);
}
