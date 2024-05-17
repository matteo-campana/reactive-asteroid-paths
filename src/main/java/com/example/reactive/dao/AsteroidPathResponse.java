package com.example.reactive.dao;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AsteroidPathResponse {
    private String fromPlanet;
    private String toPlanet;
    private LocalDate fromDate;
    private LocalDate toDate;
}
