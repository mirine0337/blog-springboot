package com.example.blog.repository;

import com.example.blog.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    // 특정 사용자가 특정 포스트에 좋아요를 눌렀는지 확인하는 메서드
    boolean existsByUserIdAndPostId(Long userId, Long postId);

    // 특정 사용자가 특정 포스트에 좋아요를 누른 기록 찾기
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);

    // 특정 포스트의 모든 좋아요 삭제 (게시글 삭제 시)
    void deleteByPostId(Long postId);
}
