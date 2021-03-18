package com.example.forum.likes;

import com.example.forum.posts.Post;
import com.example.forum.users.User;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post", referencedColumnName = "id")
    private Post post;

    private boolean value; // Either a like or a dislike

    public Like() {
    }

    public Like(long id, User user, Post post, boolean value) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.value = value;
    }

    public Like(User user, Post post, boolean value) {
        this.user = user;
        this.post = post;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
