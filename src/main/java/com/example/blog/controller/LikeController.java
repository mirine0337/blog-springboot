package com.example.blog.controller;

import com.example.blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/posts/{postId}/like")
@CrossOrigin(origins = "http://localhost:3000")
public class LikeController {

    @Autowired
    private LikeService likeService;

    // 좋아요 추가 및 취소를 처리하는 API
    @PostMapping
    public ResponseEntity<Void> likeOrUnlikePost(@PathVariable Long postId, @RequestBody Map<String, Object> requestData) {
        Long userId = Long.valueOf(requestData.get("userId").toString());  // JSON으로부터 userId를 가져옴
        Boolean liked = Boolean.valueOf(requestData.get("liked").toString());  // liked 상태를 JSON으로부터 가져옴

        if (liked) {
            likeService.likePost(postId, userId);  // 좋아요 추가
        } else {
            likeService.unlikePost(postId, userId);  // 좋아요 취소
        }

        return ResponseEntity.ok().build();
    }
}
