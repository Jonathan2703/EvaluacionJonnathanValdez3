package com.example.EvaluacionJonnathanValdez3.Posts.Services;

import com.example.EvaluacionJonnathanValdez3.Posts.Entities.Post;
import com.example.EvaluacionJonnathanValdez3.Posts.Repositories.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ExternalService {
    private final RestTemplate restTemplate;
    private final PostRepository postRepository;
    private final Logger logger= LoggerFactory.getLogger(ExternalService.class);;

    @Value("${spring.external.service.base-url}")
    private  String externalServiceUrl;

    @PostConstruct
    public void getPostsFromExternalService() {
        int maxRetries = 3;  // Número máximo de reintentos
        int retryIntervalMillis = 1000;  // Intervalo de reintento en milisegundos

        for (int retry = 0; retry < maxRetries; retry++) {
            try {
                ResponseEntity<List<Post>> response = restTemplate.exchange(
                        externalServiceUrl + "/posts",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Post>>() {
                        }
                );
                List<Post> postsFromExternalService = response.getBody();
                List<Post> existingPosts = postRepository.findAll();  // Obtener todos los posts existentes en la base de datos local

                // Filtrar solo los posts nuevos que no están en la base de datos local
                List<Post> newPosts = postsFromExternalService.stream()
                        .filter(post -> !existingPosts.contains(post))
                        .collect(Collectors.toList());

                // Guardar los nuevos posts en la base de datos local
                postRepository.saveAll(newPosts);

                logger.info("Se han agregado " + newPosts.size() + " nuevos posts desde el otro microservicio.");
                return;  // Éxito, no es necesario volver a intentar
            } catch (RestClientException e) {
                if (retry < maxRetries - 1) {
                    // Si no es el último intento, esperar antes de volver a intentar
                    try {
                        Thread.sleep(retryIntervalMillis);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    // Si es el último intento, registrar el error
                    logger.error("Error al obtener posts del servicio externo en el último intento.", e);
                }
            }
        }
    }
}
