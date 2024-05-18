package com.example.reactive.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class NasaServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @InjectMocks
    private NasaService nasaService;

    private ObjectMapper objectMapper;

    @Value("${apiKey}")
    private String apiKey;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        nasaService = new NasaService(apiKey);
    }

    @Test
    public void testGetAsteroidData() throws IOException {
        String asteroidId = "3542519";

        // Load the mock response from nasa-response.json
        String jsonResponse = new String(
                Files.readAllBytes(Paths.get(new ClassPathResource("nasa-response.json").getURI())));

        JsonNode expectedResponse = objectMapper.readTree(jsonResponse);

        Mono<JsonNode> result = nasaService.getAsteroidData(asteroidId);

        StepVerifier.create(result)
                .expectNextMatches(jsonNode -> jsonNode.equals(expectedResponse))
                .verifyComplete();
    }
}
