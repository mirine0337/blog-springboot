package com.example.blog.service;

import com.example.blog.entity.Post;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 모든 게시글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 특정 게시글 조회
    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null); // 게시글이 존재하지 않으면 null 반환
    }

    // 게시글 작성
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // 게시글 수정
    public Post updatePost(Long id, Post postDetails) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            post.setImageUrl(postDetails.getImageUrl()); // 필요한 경우 이미지 URL도 업데이트 가능

            return postRepository.save(post); // 수정 후 저장
        } else {
            return null; // 게시글이 존재하지 않으면 null 반환
        }
    }

    // 게시글 삭제
    public boolean deletePost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            postRepository.deleteById(id); // 게시글 삭제
            return true; // 삭제 성공 시 true 반환
        } else {
            return false; // 게시글이 존재하지 않으면 false 반환
        }
    }
}
