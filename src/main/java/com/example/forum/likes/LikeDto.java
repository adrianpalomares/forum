package com.example.forum.likes;

// The JSON format that the createLike() will receive in the body request.
public class LikeDto {
    private long id;
    private long userId;
    private boolean value;

    public LikeDto(long id, long userId, boolean value) {
        this.id = id;
        this.userId = userId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}