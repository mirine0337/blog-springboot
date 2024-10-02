package com.example.blog.service;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Notification;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.NotificationRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    // 특정 게시글의 댓글 목록을 불러오는 메서드
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // 새로운 댓글을 작성하는 메서드
    public Comment createComment(Long postId, Long userId, String content) {
        // 게시글을 찾음
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

        // 댓글 작성자를 찾음
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        // 새로운 댓글을 생성
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(content);

        // 댓글 저장
        Comment savedComment = commentRepository.save(comment);

        // 알림 생성 (게시글 작성자에게 알림 전송)
        if (!post.getUser().getId().equals(userId)) {  // 작성자가 본인인 경우 알림을 생성하지 않음
            Notification notification = new Notification();
            notification.setUser(post.getUser());  // 게시글 작성자에게 알림을 보냄
            notification.setMessage(user.getUsername() + "님이 당신의 게시글에 댓글을 달았습니다: " + content);
            notification.setPost(post);  // 알림에 게시글 정보 추가
            notificationRepository.save(notification);  // 알림 저장
        }

        return savedComment;
    }
}
