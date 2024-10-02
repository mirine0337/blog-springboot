package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")  // users 테이블과 매핑
public class User {

    // Getters 및 Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;  // 사용자 이름 (닉네임)

    @Column(nullable = false, unique = true, length = 100)
    private String email;  // 이메일

    @Column(nullable = false, length = 255)
    private String password;  // 비밀번호

    @Column(name = "profile_image_url", length = 255)
    private String profileImageUrl;  // 프로필 이미지 URL (선택 사항)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_USER;  // 역할 (기본값은 USER)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // 계정 생성 날짜

    @Column(name = "last_login")
    private LocalDateTime lastLogin;  // 마지막 로그인 날짜 (선택 사항)

    // 역할 Enum 정의
    public enum Role {
        ROLE_USER,
        ROLE_ADMIN
    }

    // 기본 생성자
    public User() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}
