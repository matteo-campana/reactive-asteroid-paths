package com.example.reactive.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;

import com.example.reactive.model.AsteroidPassage;
import com.example.reactive.model.AsteroidPath;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AsteroidPathService {
    @Autowired
    private NasaService nasaService;

    @Cacheable("AsteroidPathsCache")
    public Flux<AsteroidPath> getAsteroidPaths(String asteroidId, LocalDate fromDate, LocalDate toDate) {

        Mono<JsonNode> asteroidDataJson = nasaService.getAsteroidData(asteroidId);

        return asteroidDataJson.map(nasaData -> MapNasaDataToAsteroidPathResponse(nasaData, fromDate, toDate))
                .flatMapMany(Flux::fromIterable);

    }

    private List<AsteroidPath> MapNasaDataToAsteroidPathResponse(JsonNode nasaData, LocalDate fromDate,
            LocalDate toDate) {

        JsonNode close_approach_data = nasaData.get("close_approach_data");

        List<AsteroidPassage> asteroidPassages = new ArrayList<>();

        for (JsonNode close_approach : close_approach_data) {
            LocalDate closeApproachDate = LocalDate.parse(close_approach.get("close_approach_date").asText());

            if (closeApproachDate.isAfter(fromDate) && closeApproachDate.isBefore(toDate)) {
                AsteroidPassage asteroidPassage = AsteroidPassage.builder()
                        .closeApproachDate(closeApproachDate)
                        .orbitingBody(close_approach.get("orbiting_body").asText())
                        .build();

                asteroidPassages.add(asteroidPassage);
            }
        }

        // sort in ascending order the asteroidPassages by closeApproachDate
        asteroidPassages.sort((a, b) -> a.getCloseApproachDate().compareTo(b.getCloseApproachDate()));

        LocalDate previousCloseApproachDate = null;
        String previousOrbitingBody = null;
        List<AsteroidPath> asteroidPaths = new ArrayList<>();

        for (AsteroidPassage asteroidPassage : asteroidPassages) {
            if (previousOrbitingBody == null) {
                previousOrbitingBody = asteroidPassage.getOrbitingBody();
                previousCloseApproachDate = asteroidPassage.getCloseApproachDate();
                continue;
            }

            if (previousOrbitingBody.equals(asteroidPassage.getOrbitingBody())) {
                continue;
            }

            if (!asteroidPassage.getOrbitingBody().equals(previousOrbitingBody)) {
                AsteroidPath asteroidPath = AsteroidPath.builder()
                        .fromPlanet(previousOrbitingBody)
                        .toPlanet(asteroidPassage.getOrbitingBody())
                        .fromDate(previousCloseApproachDate)
                        .toDate(asteroidPassage.getCloseApproachDate())
                        .build();

                asteroidPaths.add(asteroidPath);

                previousCloseApproachDate = asteroidPassage.getCloseApproachDate();
                previousOrbitingBody = asteroidPassage.getOrbitingBody();
            }

        }

        return asteroidPaths;
    }

    @SuppressWarnings("unused")
    private JsonNode MapNasaDataToAsteroidPathResponseJson(JsonNode nasaData, LocalDate fromDate, LocalDate toDate) {

        List<AsteroidPath> asteroidPaths = MapNasaDataToAsteroidPathResponse(nasaData, fromDate, toDate);

        JsonNode resultjsonNode = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(asteroidPaths);
            resultjsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            // Handle the exception here, e.g. log the error or throw a custom exception
            e.printStackTrace();
        }

        return resultjsonNode;
    }

}