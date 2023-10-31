package com.example.EvaluacionJonnathanValdez3.service;

import com.example.EvaluacionJonnathanValdez3.entities.Post;
import com.example.EvaluacionJonnathanValdez3.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalService {
    private final RestTemplate restTemplate;
    private final PostRepository postRepository;
    private final Logger logger= LoggerFactory.getLogger(ExternalService.class);;

    @Value("${spring.external.service.base-url}")
    private  String externalServiceUrl;

    @PostConstruct
    public void getPostsFromExternalService(){
        try {
            ResponseEntity<List<Post>> response = restTemplate.exchange(
                    externalServiceUrl+"/posts",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Post>>() {
                    }
            );
            List<Post> posts = response.getBody();
            postRepository.saveAll(posts);
            logger.info("Los post han sido creados desde el otro microservicio");
        }catch (RestClientException e){
            e.printStackTrace(); // Puedes personalizar esto para registrar errores de la manera que prefieras.
        }
    }
}
