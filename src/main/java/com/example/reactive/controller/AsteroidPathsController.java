package com.example.reactive.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import com.example.reactive.model.AsteroidPath;
import com.example.reactive.service.AsteroidPathService;

import java.time.LocalDate;

@RestController
public class AsteroidPathsController {

    private final AsteroidPathService asteroidPathService;

    public AsteroidPathsController(AsteroidPathService asteroidPathService) {
        this.asteroidPathService = asteroidPathService;
    }

    @Cacheable("AsteroidsPaths")
    @GetMapping(value = "/api/fabrick/v1.0/asteroids/{asteroidId}/paths", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AsteroidPath> getAsteroidPaths(@PathVariable String asteroidId,
            @RequestParam(value = "fromDate", defaultValue = "#{T(java.time.LocalDate).now().minusYears(100)}") LocalDate fromDate,
            @RequestParam(value = "toDate", defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate toDate) {
        return asteroidPathService.getAsteroidPaths(asteroidId, fromDate, toDate);
    }
}