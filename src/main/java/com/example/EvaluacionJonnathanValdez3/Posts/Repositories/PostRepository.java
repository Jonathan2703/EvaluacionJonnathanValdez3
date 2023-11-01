package com.example.EvaluacionJonnathanValdez3.Posts.Repositories;

import com.example.EvaluacionJonnathanValdez3.Posts.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.id = :id OR p.title = :title")
    List<Post> searchByIdOrTitle(@Param("id") Long id, @Param("title") String title);
}
