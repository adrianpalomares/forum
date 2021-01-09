package com.example.forum.users;

import javax.persistence.*;
import java.time.Instant;
// TODO: Add validation "spring-boot-strarter-validation"
// TODO: Make username unique

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String email;
    private Instant created;

    public User() {
    }

    public User(long id, String username, String password, String email, Instant created) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

}
