package com.example.blog.controller;

import com.example.blog.entity.Post;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000") // React 앱의 Origin 허용
public class PostController {

    @Autowired
    private PostService postService;

    // 글 작성 API
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost); // 201 Created 반환
    }

    // 글 목록 조회 API
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 특정 글 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found 반환
        }
    }

    // 글 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post updatedPost = postService.updatePost(id, postDetails);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found 반환
        }
    }

    // 글 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean isDeleted = postService.deletePost(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content 반환
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found 반환
        }
    }
}
