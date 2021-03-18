package com.example.forum.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LikeController {
    @Autowired
    LikeService likeService;

    @CrossOrigin(origins = "*")
    @GetMapping("api/likes")
    public List<Like> getLikesByPost(@RequestParam("post") long postId) {
        // Get likes
        List<Like> listOfLikes = likeService.getLikesByPost(postId);
        return listOfLikes;
    }
}