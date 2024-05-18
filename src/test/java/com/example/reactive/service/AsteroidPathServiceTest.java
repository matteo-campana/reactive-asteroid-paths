package com.example.reactive.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;

import com.example.reactive.model.AsteroidPath;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class AsteroidPathServiceTest {

    @Autowired
    private AsteroidPathService asteroidPathService;

    @MockBean
    private NasaService nasaService;

    @Test
    void testGetAsteroidPaths() throws IOException {
        String asteroidId = "3542519";
        LocalDate fromDate = LocalDate.of(2000, 1, 1);
        LocalDate toDate = LocalDate.of(2024, 1, 1);

        // Load the mock response from nasa-response.json
        String getAsteroidDataResponseString = new String(
                Files.readAllBytes(Paths.get(new ClassPathResource("nasa-response.json").getURI())));

        ObjectMapper mapper = new ObjectMapper();
        JsonNode getAsteroidDataResponseJson = mapper.readTree(getAsteroidDataResponseString);

        // Mock the nasaService to return the jsonResponse
        when(nasaService.getAsteroidData(asteroidId)).thenReturn(Mono.just(getAsteroidDataResponseJson));

        // Load the mock response from nasa-response.json
        String getAsteroidPathsResponseString = new String(
                Files.readAllBytes(Paths.get(new ClassPathResource("api-response.json").getURI())));

        JsonNode getAsteroidPathsResponseJson = mapper.readTree(getAsteroidPathsResponseString);

        JsonNode arrayNode = getAsteroidPathsResponseJson.get("data");
        assertNotNull(arrayNode, "The 'data' array should not be null");
        assertTrue(arrayNode.isArray(), "The 'data' should be an array");

        List<AsteroidPath> asteroidPaths = new ArrayList<>();

        if (arrayNode.isArray()) {
            for (JsonNode objNode : arrayNode) {
                AsteroidPath asteroidPath = AsteroidPath.builder()
                        .fromPlanet(objNode.get("fromPlanet").asText())
                        .toPlanet(objNode.get("toPlanet").asText())
                        .fromDate(LocalDate.parse(objNode.get("fromDate").asText()))
                        .toDate(LocalDate.parse(objNode.get("toDate").asText()))
                        .build();
                asteroidPaths.add(asteroidPath);
            }
        }

        asteroidPaths.sort((a, b) -> a.getFromDate().compareTo(b.getFromDate()));

        Flux<AsteroidPath> result = asteroidPathService.getAsteroidPaths(asteroidId, fromDate, toDate);

        StepVerifier.create(result)
                .expectNextSequence(asteroidPaths)
                .verifyComplete();
    }
}
