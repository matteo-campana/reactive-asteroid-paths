package com.example.reactive.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class NasaService {

    @Value("${apiKey}")
    private String apiKey;

    // "https://api.nasa.gov/neo/rest/v1/neo/3542519"
    // "https://api.nasa.gov/neo/rest/v1/neo/{asteroidId}?api_key={apiKey}"
    private final String apiUrl = "https://api.nasa.gov/neo/rest/v1/neo/";

    @Cacheable("nasaDataCache")
    public Mono<JsonNode> getAsteroidData(String asteroidId) {

        // System.out.println("Fetching data from NASA API for asteroidId: " +
        // asteroidId);

        String uri = apiUrl + "/" + asteroidId;

        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("api_key", apiKey)
                .toUriString();
        String jsonResponse = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(jsonNode);
    }
}
