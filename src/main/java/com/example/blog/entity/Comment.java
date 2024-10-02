package com.example.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;  // Post와의 관계

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // User와의 관계

    @Column(nullable = false)
    private String content;  // 댓글 내용

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // 댓글 작성 시간

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();  // 댓글 수정 시간

    // 기본 생성자
    public Comment() {}

    // Getters 및 Setters

    // id Getter
    public Long getId() {
        return id;
    }

    // id Setter
    public void setId(Long id) {
        this.id = id;
    }

    // post Getter
    public Post getPost() {
        return post;
    }

    // post Setter
    public void setPost(Post post) {
        this.post = post;
    }

    // user Getter
    public User getUser() {
        return user;
    }

    // user Setter
    public void setUser(User user) {
        this.user = user;
    }

    // content Getter
    public String getContent() {
        return content;
    }

    // content Setter
    public void setContent(String content) {
        this.content = content;
    }

    // createdAt Getter
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // updatedAt Getter
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // updatedAt Setter (필요 시)
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
