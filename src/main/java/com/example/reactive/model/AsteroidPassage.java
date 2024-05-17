package com.example.reactive.model;

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
public class AsteroidPassage {
    private LocalDate closeApproachDate;
    private String orbitingBody;
}