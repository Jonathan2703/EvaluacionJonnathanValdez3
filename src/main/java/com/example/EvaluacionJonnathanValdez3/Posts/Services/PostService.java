package com.example.EvaluacionJonnathanValdez3.Posts.Services;

import com.example.EvaluacionJonnathanValdez3.Posts.Entities.Post;
import com.example.EvaluacionJonnathanValdez3.Posts.Repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public List<Post> searchPosts(Long id, String title) {
        return postRepository.searchByIdOrTitle(id, title);
    }
}
