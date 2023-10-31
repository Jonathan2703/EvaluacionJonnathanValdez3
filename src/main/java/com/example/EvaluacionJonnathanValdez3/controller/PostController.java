package com.example.EvaluacionJonnathanValdez3.controller;

import com.example.EvaluacionJonnathanValdez3.entities.Post;
import com.example.EvaluacionJonnathanValdez3.service.PostService;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(
            @RequestParam(name = "id", required = false) @Positive(message = "El ID tiene que ser postivo") Long id,
            @RequestParam(name = "title", required = false) String title) {

        List<Post> posts = postService.searchPosts(id, title);

        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
