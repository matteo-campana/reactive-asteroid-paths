package com.example.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactive.service.NasaService;
import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class NasaController {

    @Autowired
    private NasaService nasaService;

    // @GetMapping("/neo")
    // public Map<String, Object> getNeoData() {
    // return nasaService.getNasaData();
    // }

    @Cacheable("neo")
    @GetMapping("/neo/{asteroidId}")
    public Mono<JsonNode> getNeoData(
            @PathVariable String asteroidId) {
        return nasaService.getNasaData(asteroidId);
    }
}
