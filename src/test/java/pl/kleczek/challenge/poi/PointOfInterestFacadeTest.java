package pl.kleczek.challenge.poi;

import org.junit.jupiter.api.Test;
import pl.kleczek.challenge.poi.infra.InMemoryPointOfInterestRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.kleczek.challenge.poi.CoordinatesFixtures.*;

class PointOfInterestFacadeTest {
    InMemoryPointOfInterestRepository pointOfInterestRepository = new InMemoryPointOfInterestRepository();
    PointOfInterestFacade facade = new PoiConfiguration().pointOfInterestFacade(pointOfInterestRepository);

    @Test
    void itShouldReturnMatchingPointsByNameAndDistance() {
        setupPoints();

        Flux<PointOfInterest> points = facade.findPoints("gAleRi", KRAKOW);

        StepVerifier.create(points)
                .assertNext(p -> assertThat(p).extracting(PointOfInterest::name)
                        .isEqualTo("A very nice Galeria!"))
                .assertNext(p -> assertThat(p).extracting(PointOfInterest::name)
                        .isEqualTo("A place in Cape Town which is a Galeria"))
                .verifyComplete();
    }

    @Test
    void itShouldReturnNothingWhenNoPointsMatched() {
        setupPoints();

        Flux<PointOfInterest> points = facade.findPoints("you can't find this", KRAKOW);

        StepVerifier.create(points)
                .verifyComplete();
    }

    private void setupPoints() {
        pointOfInterestRepository
                .setupPoint(NEW_YORK, "Not important")
                .setupPoint(WARSAW, "Another one")
                .setupPoint(WARSAW, "A very nice Galeria!")
                .setupPoint(BUENOS_AIRES, "Not the thing you are looking for")
                .setupPoint(CAPE_TOWN, "A place in Cape Town which is a Galeria");
    }
}