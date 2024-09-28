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

    // 글 작성 서비스 (이미 존재함)
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // 모든 글 조회 서비스
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 특정 글 조회 서비스
    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new RuntimeException("Post not found"));
    }
}
