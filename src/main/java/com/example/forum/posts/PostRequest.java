package com.example.forum.posts;

public class PostRequest {

    private Long id;
    private String title;
    private Long userId;
    private String text;

    public PostRequest() {
    }

    public PostRequest(Long id, String title, Long userId, String text) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
