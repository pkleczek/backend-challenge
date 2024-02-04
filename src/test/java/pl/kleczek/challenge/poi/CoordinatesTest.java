package pl.kleczek.challenge.poi;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.kleczek.challenge.poi.CoordinatesFixtures.*;

class CoordinatesTest {
    @Test
    void distanceBetweenTheSamePointsIsZero() {
        assertEquals(0.0, KRAKOW.distance(KRAKOW));
    }

    @Test
    void distanceBetweenTwoPointsIsReflexive() {
        double fromNewYorkToWarsaw = NEW_YORK.distance(WARSAW);
        double fromWarsawToNewYork = WARSAW.distance(NEW_YORK);
        assertEquals(fromNewYorkToWarsaw, fromWarsawToNewYork);
    }

    @Test
    void its8527kmFromNewYorkToBuenosAires() {
        assertThat(NEW_YORK.distance(BUENOS_AIRES))
                .isCloseTo(8527, offset(0.1));
    }
}