
package com.example.reactive.controller;

import com.example.reactive.service.NasaService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class NasaControllerTest {

    private NasaService nasaService;
    private NasaController nasaController;
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        nasaService = Mockito.mock(NasaService.class);
        nasaController = new NasaController(nasaService);
        webTestClient = WebTestClient.bindToController(nasaController).build();
    }

    @Test
    public void testGetNeoData() throws Exception {
        JsonNode jsonNode = new ObjectMapper().readTree("{\"key\":\"value\"}"); // Replace with appropriate JSON
        Mono<JsonNode> jsonNodeMono = Mono.just(jsonNode);

        Mockito.when(nasaService.getAsteroidData(Mockito.anyString())).thenReturn(jsonNodeMono);

        webTestClient.get()
                .uri("/api/neo/{asteroidId}", "testAsteroidId")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(JsonNode.class)
                .getResponseBody()
                .as(StepVerifier::create)
                .expectNext(jsonNode)
                .verifyComplete();

        Mockito.verify(nasaService, Mockito.times(1)).getAsteroidData(Mockito.anyString());
    }
}