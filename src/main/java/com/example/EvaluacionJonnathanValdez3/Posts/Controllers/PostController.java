package com.example.EvaluacionJonnathanValdez3.Posts.Controllers;

import com.example.EvaluacionJonnathanValdez3.Posts.Entities.Post;
import com.example.EvaluacionJonnathanValdez3.Posts.Services.PostService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(
            @RequestParam(name = "id", required = false) @NotEmpty(message = "El ID no puede ser nulo o vacio") @Pattern(regexp = "^[0-9]+$", message = "Tiene que ser un numero")  String id,
            @RequestParam(name = "title", required = false) @NotEmpty(message = "El ID no puede ser nulo o vacio") String title) {

        Long idAsLong = null;

        if (id != null) {
            idAsLong = Long.parseLong(id);
        }

        List<Post> posts = postService.searchPosts(idAsLong, title);

        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
