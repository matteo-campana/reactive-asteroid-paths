package com.example.reactive.service;

import com.example.reactive.model.AsteroidPath;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusDays(1);

        JsonNode close_approach_data = mock(JsonNode.class);
        when(jsonNode.get("close_approach_data")).thenReturn(close_approach_data);
        when(close_approach_data.iterator()).thenReturn(Collections.emptyIterator());

        when(nasaService.getAsteroidData(asteroidId)).thenReturn(Mono.just(jsonNode));

        Flux<AsteroidPath> result = asteroidPathService.getAsteroidPaths(asteroidId, fromDate, toDate);

        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }
}