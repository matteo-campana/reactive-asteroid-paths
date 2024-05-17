package com.example.reactive.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.reactive.model.AsteroidPath;

import reactor.core.publisher.Flux;

@Service
public class AsteroidPathService {
    @Autowired
    private NasaService nasaService;

    @Cacheable("AsteroidPathCache")
    public Flux<AsteroidPath> getAsteroidPaths(String asteroidId, LocalDate fromDate, LocalDate toDate) {

        if (fromDate == null) {
            fromDate = LocalDate.now().minusYears(100);
        }

        if (toDate == null) {
            toDate = LocalDate.now();
        }

        return null;
    }

}