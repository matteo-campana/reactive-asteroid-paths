package com.example.reactive.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize
public class AsteroidPath implements Serializable {

    private String fromPlanet;
    private String toPlanet;
    private LocalDate fromDate;
    private LocalDate toDate;

}
