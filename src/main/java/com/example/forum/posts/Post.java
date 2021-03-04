package com.example.forum.posts;

import com.example.forum.users.User;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String text;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    public Post() {
    }

    public Post(long id, String title, User user, String text) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
