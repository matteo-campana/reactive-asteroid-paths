package com.example.reactive.service;

import static org.mockito.Mockito.when;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;

import com.example.reactive.model.AsteroidPath;
import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class AsteroidPathServiceTest {

    @Autowired
    private AsteroidPathService asteroidPathService;

    @MockBean
    private NasaService nasaService;

    @MockBean
    private JsonNode jsonNode;

    @Test
    void testGetAsteroidPaths() {
        String asteroidId = "3542519";
        LocalDate fromDate = LocalDate.of(2000, 1, 1);
        LocalDate toDate = LocalDate.of(2024, 1, 1);

        when(nasaService.getAsteroidData(asteroidId)).thenReturn(Mono.just(jsonNode));

        // Load the mock response from nasa-response.json
        String jsonResponse = new String(
                Files.readAllBytes(Paths.get(new ClassPathResource("api-response.json").getURI())));

        Flux<AsteroidPath> result = asteroidPathService.getAsteroidPaths(asteroidId, fromDate, toDate);

        StepVerifier.create(result)
                .thenRequest(5)
                .expectNextCount(5)
                .thenCancel()
                .verify();
        // .verifyComplete();

    }
}