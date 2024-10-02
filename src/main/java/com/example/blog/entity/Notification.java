package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)  // post_id 필드 추가, nullable 허용
    private Post post;  // Notification이 참조하는 Post

    private String message;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;  // 읽음 여부 필드 추가, 기본값은 false

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 기본 생성자
    public Notification() {
    }

    // 생성자
    public Notification(User user, Post post, String message) {  // Post 추가
        this.user = user;
        this.post = post;  // 알림이 참조하는 게시글
        this.message = message;
        this.isRead = false;  // 기본값으로 읽음 여부 false 설정
        this.createdAt = LocalDateTime.now();
    }

    // Getter와 Setter 메서드

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
