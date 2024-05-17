package com.example.reactive.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AsteroidPathTest {

    @Test
    void testBuilder() {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusDays(1);
        String fromPlanet = "Earth";
        String toPlanet = "Mars";

        AsteroidPath asteroidPath = AsteroidPath.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .fromPlanet(fromPlanet)
                .toPlanet(toPlanet)
                .build();

        assertThat(asteroidPath.getFromDate()).isEqualTo(fromDate);
        assertThat(asteroidPath.getToDate()).isEqualTo(toDate);
        assertThat(asteroidPath.getFromPlanet()).isEqualTo(fromPlanet);
        assertThat(asteroidPath.getToPlanet()).isEqualTo(toPlanet);
    }

    @Test
    void testCanEqual() {
        AsteroidPath asteroidPath1 = new AsteroidPath();
        AsteroidPath asteroidPath2 = new AsteroidPath();

        assertThat(asteroidPath1.canEqual(asteroidPath2)).isTrue();
    }

    @Test
    void testEquals() {
        AsteroidPath asteroidPath1 = new AsteroidPath();
        AsteroidPath asteroidPath2 = new AsteroidPath();

        assertThat(asteroidPath1).isEqualTo(asteroidPath2);
    }

    @Test
    void testGetFromDate() {
        LocalDate fromDate = LocalDate.now();
        AsteroidPath asteroidPath = new AsteroidPath();
        asteroidPath.setFromDate(fromDate);

        assertThat(asteroidPath.getFromDate()).isEqualTo(fromDate);
    }

    @Test
    void testGetFromPlanet() {
        String fromPlanet = "Earth";
        AsteroidPath asteroidPath = new AsteroidPath();
        asteroidPath.setFromPlanet(fromPlanet);

        assertThat(asteroidPath.getFromPlanet()).isEqualTo(fromPlanet);
    }

    @Test
    void testGetToDate() {
        LocalDate toDate = LocalDate.now().plusDays(1);
        AsteroidPath asteroidPath = new AsteroidPath();
        asteroidPath.setToDate(toDate);

        assertThat(asteroidPath.getToDate()).isEqualTo(toDate);
    }

    @Test
    void testGetToPlanet() {
        String toPlanet = "Mars";
        AsteroidPath asteroidPath = new AsteroidPath();
        asteroidPath.setToPlanet(toPlanet);

        assertThat(asteroidPath.getToPlanet()).isEqualTo(toPlanet);
    }

    @Test
    void testHashCode() {
        AsteroidPath asteroidPath = new AsteroidPath();
        int hashCode = asteroidPath.hashCode();

        assertThat(hashCode).isNotNull();
    }

    @Test
    void testSetFromDate() {
        LocalDate fromDate = LocalDate.now();
        AsteroidPath asteroidPath = new AsteroidPath();
        asteroidPath.setFromDate(fromDate);

        assertThat(asteroidPath.getFromDate()).isEqualTo(fromDate);
    }

    @Test
    void testSetFromPlanet() {
        String fromPlanet = "Earth";
        AsteroidPath asteroidPath = new AsteroidPath();
        asteroidPath.setFromPlanet(fromPlanet);

        assertThat(asteroidPath.getFromPlanet()).isEqualTo(fromPlanet);
    }

    @Test
    void testSetToDate() {
        LocalDate toDate = LocalDate.now().plusDays(1);
        AsteroidPath asteroidPath = new AsteroidPath();
        asteroidPath.setToDate(toDate);

        assertThat(asteroidPath.getToDate()).isEqualTo(toDate);
    }

    @Test
    void testSetToPlanet() {
        String toPlanet = "Mars";
        AsteroidPath asteroidPath = new AsteroidPath();
        asteroidPath.setToPlanet(toPlanet);

        assertThat(asteroidPath.getToPlanet()).isEqualTo(toPlanet);
    }

    @Test
    void testToString() {
        AsteroidPath asteroidPath = new AsteroidPath();
        String asteroidPathString = asteroidPath.toString();

        assertThat(asteroidPathString).isNotNull();
    }
}