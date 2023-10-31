package com.example.EvaluacionJonnathanValdez3.controller;

import com.example.EvaluacionJonnathanValdez3.entities.Post;
import com.example.EvaluacionJonnathanValdez3.repository.PostRepository;
import com.example.EvaluacionJonnathanValdez3.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final ExternalService externalService;

    @GetMapping("/import")
    public ResponseEntity<String> importData() {
        try {
            externalService.getPostsFromExternalService();
            return ResponseEntity.ok("Datos importados correctamente.");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al importar datos.");
        }
    }
}
