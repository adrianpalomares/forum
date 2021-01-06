package com.example.forum.posts;

public class PostRequest {

    private Long id;

        public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String title;
    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
