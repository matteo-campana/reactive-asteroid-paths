package com.example.reactive.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AsteroidPassageTest {

    @Test
    void testBuilder() {
        LocalDate testDate = LocalDate.now();
        String testOrbitingBody = "Mars";

        AsteroidPassage asteroidPassage = AsteroidPassage.builder()
                .closeApproachDate(testDate)
                .orbitingBody(testOrbitingBody)
                .build();

        assertThat(asteroidPassage.getCloseApproachDate()).isEqualTo(testDate);
        assertThat(asteroidPassage.getOrbitingBody()).isEqualTo(testOrbitingBody);
    }

    @Test
    void testCanEqual() {
        AsteroidPassage asteroidPassage1 = new AsteroidPassage();
        AsteroidPassage asteroidPassage2 = new AsteroidPassage();

        assertThat(asteroidPassage1.canEqual(asteroidPassage2)).isTrue();
    }

    @Test
    void testEquals() {
        AsteroidPassage asteroidPassage1 = new AsteroidPassage();
        AsteroidPassage asteroidPassage2 = new AsteroidPassage();

        assertThat(asteroidPassage1).isEqualTo(asteroidPassage2);
    }

    @Test
    void testGetCloseApproachDate() {
        LocalDate testDate = LocalDate.now();
        AsteroidPassage asteroidPassage = new AsteroidPassage();
        asteroidPassage.setCloseApproachDate(testDate);

        assertThat(asteroidPassage.getCloseApproachDate()).isEqualTo(testDate);
    }

    @Test
    void testGetOrbitingBody() {
        String testOrbitingBody = "Mars";
        AsteroidPassage asteroidPassage = new AsteroidPassage();
        asteroidPassage.setOrbitingBody(testOrbitingBody);

        assertThat(asteroidPassage.getOrbitingBody()).isEqualTo(testOrbitingBody);
    }

    @Test
    void testHashCode() {
        AsteroidPassage asteroidPassage = new AsteroidPassage();
        int hashCode = asteroidPassage.hashCode();

        assertThat(hashCode).isNotNull();
    }

    @Test
    void testSetCloseApproachDate() {
        LocalDate testDate = LocalDate.now();
        AsteroidPassage asteroidPassage = new AsteroidPassage();
        asteroidPassage.setCloseApproachDate(testDate);

        assertThat(asteroidPassage.getCloseApproachDate()).isEqualTo(testDate);
    }

    @Test
    void testSetOrbitingBody() {
        String testOrbitingBody = "Mars";
        AsteroidPassage asteroidPassage = new AsteroidPassage();
        asteroidPassage.setOrbitingBody(testOrbitingBody);

        assertThat(asteroidPassage.getOrbitingBody()).isEqualTo(testOrbitingBody);
    }

    @Test
    void testToString() {
        AsteroidPassage asteroidPassage = new AsteroidPassage();
        String asteroidPassageString = asteroidPassage.toString();

        assertThat(asteroidPassageString).isNotNull();
    }
}