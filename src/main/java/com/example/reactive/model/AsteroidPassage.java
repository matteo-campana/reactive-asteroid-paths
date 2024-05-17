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
public class AsteroidPassage {
    private LocalDate closeApproachDate;
    private String orbitingBody;
}