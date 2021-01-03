package com.example.forum.posts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
}
