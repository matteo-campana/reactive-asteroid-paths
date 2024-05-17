package com.example.reactive.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AsteroidPath {
    // {
    // "fromPlanet": "Juptr",
    // "toPlanet": "Earth",
    // "fromDate": "1917-04-30",
    // "toDate": "1930-06-01"
    // },

    private String fromPlanet;
    private String toPlanet;
    private LocalDate fromDate;
    private LocalDate toDate;

}