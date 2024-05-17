
package com.example.reactive.controller;

import com.example.reactive.model.AsteroidPath;
import com.example.reactive.service.AsteroidPathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

public class AsteroidPathsControllerTest {

    private AsteroidPathService asteroidPathService;
    private AsteroidPathsController asteroidPathsController;
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        asteroidPathService = Mockito.mock(AsteroidPathService.class);
        asteroidPathsController = new AsteroidPathsController(asteroidPathService);
        webTestClient = WebTestClient.bindToController(asteroidPathsController).build();
    }

    @Test
    public void testGetAsteroidPaths() {
        AsteroidPath asteroidPath = new AsteroidPath(); // Initialize with appropriate values
        Flux<AsteroidPath> asteroidPathFlux = Flux.just(asteroidPath);

        Mockito.when(asteroidPathService.getAsteroidPaths(Mockito.anyString(), Mockito.any(LocalDate.class),
                Mockito.any(LocalDate.class)))
                .thenReturn(asteroidPathFlux);

        webTestClient.get()
                .uri("/api/fabrick/v1.0/asteroids/{asteroidId}/paths", "testAsteroidId")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.TEXT_EVENT_STREAM_VALUE + ";charset=UTF-8")
                .returnResult(AsteroidPath.class)
                .getResponseBody()
                .as(StepVerifier::create)
                .expectNext(asteroidPath)
                .verifyComplete();

        Mockito.verify(asteroidPathService, Mockito.times(1)).getAsteroidPaths(Mockito.anyString(),
                Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
    }
}