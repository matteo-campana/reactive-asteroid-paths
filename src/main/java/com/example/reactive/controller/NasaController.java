package com.example.reactive.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactive.service.NasaService;
import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@RestController
public class NasaController {

    private final NasaService nasaService;

    public NasaController(NasaService nasaService) {
        this.nasaService = nasaService;
    }

    @Cacheable("neo")
    @GetMapping("/api/neo/{asteroidId}")
    public Mono<JsonNode> getNeoData(
            @PathVariable String asteroidId) {
        return nasaService.getAsteroidData(asteroidId);
    }
}
