package com.example.blog.dto;  // 적절한 패키지로 수정

public class CommentRequest {
    private Long userId;  // 사용자 ID
    private String content;  // 댓글 내용

    // 기본 생성자
    public CommentRequest() {}

    // Getters 및 Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
