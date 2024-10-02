package com.example.blog.controller;

import com.example.blog.dto.CommentRequest;
import com.example.blog.entity.Comment;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@CrossOrigin(origins = "http://localhost:3000")  // React 앱과의 통신 허용
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 특정 게시글의 댓글 목록 조회
    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 작성
    @PostMapping
    public ResponseEntity<Comment> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest commentRequest) {  // RequestBody로 댓글 데이터 받기
        Comment comment = commentService.createComment(postId, commentRequest.getUserId(), commentRequest.getContent());
        return ResponseEntity.ok(comment);
    }
}
