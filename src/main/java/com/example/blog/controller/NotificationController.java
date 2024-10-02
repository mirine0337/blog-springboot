package com.example.blog.controller;

import com.example.blog.entity.Notification;
import com.example.blog.entity.Post;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.NotificationRepository;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:3000")  // 프론트엔드와의 CORS 문제 해결
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private PostRepository postRepository;  // 게시글 정보와 연결하기 위한 PostRepository 추가

    // 해당 사용자의 알림 목록 가져오기
    @GetMapping("/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getNotifications(@PathVariable Long userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);

        // 알림 리스트를 변환하여 postId를 포함한 데이터를 반환
        List<Map<String, Object>> response = notifications.stream().map(notification -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", notification.getId());
            map.put("message", notification.getMessage());
            map.put("isRead", notification.getIsRead());
            map.put("createdAt", notification.getCreatedAt());
            map.put("postId", notification.getPost() != null ? notification.getPost().getId() : null);  // postId 추가
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // 알림을 읽음 상태로 변경 및 게시글 정보 반환
    @PutMapping("/{id}/read")  // PUT 메서드 사용 (리소스 업데이트)
    public ResponseEntity<Map<String, Object>> markAsRead(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));

        notification.setIsRead(true);  // 읽음 상태로 변경
        notificationRepository.save(notification);  // 변경 사항 저장

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("notification", notification);

        // 알림에 연관된 게시글이 있을 경우 해당 게시글 정보도 응답에 포함
        if (notification.getPost() != null) {
            Post post = postRepository.findById(notification.getPost().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
            response.put("post", post);  // 게시글 정보 추가
        }

        return ResponseEntity.ok(response);
    }

}
