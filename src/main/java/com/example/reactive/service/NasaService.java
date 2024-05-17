package com.example.reactive.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;

@Service
public class NasaService {
    @Value("${apiKey}")
    private String apiKey;

    // "https://api.nasa.gov/neo/rest/v1/neo/3542519"
    // "https://api.nasa.gov/neo/rest/v1/neo/{asteroidId}?api_key={apiKey}"
    private final String apiUrl = "https://api.nasa.gov/neo/rest/v1/neo/";

    // public Map<String, Object> getNasaData() {
    // RestTemplate restTemplate = new RestTemplate();
    // String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
    // .queryParam("api_key", apiKey)
    // .toUriString();
    // return restTemplate.getForObject(url, Map.class);
    // }

    @Cacheable("nasaDataCache")
    public JsonNode getNasaData(String asteroidId) {

        System.out.println("Fetching data from NASA API for asteroidId: " + asteroidId);

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
        return jsonNode;
    }

    private JsonNode MapNasaDataToAsteroidPathResponse(JsonNode nasaData) {
        return null;
    }
}