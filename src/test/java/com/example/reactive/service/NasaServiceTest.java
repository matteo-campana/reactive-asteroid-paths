
package com.example.reactive.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
public class NasaServiceTest {

    @Autowired
    private NasaService nasaService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private JsonNode jsonNode;

    @Value("${apiKey}")
    private String apiKey;

    @Test
    void testGetAsteroidData() {
        String asteroidId = "3542519";
        String jsonResponse = "{}";

        when(restTemplate.getForObject(
                "https://api.nasa.gov/neo/rest/v1/neo/" + asteroidId + "?api_key=" + apiKey,
                String.class)).thenReturn(jsonResponse);

        Mono<JsonNode> result = nasaService.getAsteroidData(asteroidId);

        StepVerifier.create(result)
                .expectNextMatches(jsonNode -> jsonNode != null)
                .verifyComplete();
    }
}