package pl.kleczek.challenge.poi.infra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.kleczek.challenge.poi.Coordinates;
import pl.kleczek.challenge.poi.PointOfInterestFacade;

import static org.mockito.Mockito.verify;
import static pl.kleczek.challenge.poi.CoordinatesFixtures.KRAKOW;

@WebFluxTest(PointOfInterestController.class)
class PointOfInterestControllerTest {
    @MockBean
    PointOfInterestFacade facade;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void itResolvesBothParameters() {
        webTestClient
                .get()
                .uri(b -> b
                        .path("/find")
                        .queryParam("name", "test")
                        .queryParam("position", "%f, %f".formatted(KRAKOW.latitude(), KRAKOW.longitude()))
                        .build()
                )
                .exchange()
                .expectStatus()
                .isOk();

        verify(facade).findPoints("test", KRAKOW);
    }

    @Test
    void itFallsBackToZeroCoordinatesWhenNothingProvided() {
        webTestClient
                .get()
                .uri(b -> b
                        .path("/find")
                        .queryParam("name", "test")
                        .build()
                )
                .exchange()
                .expectStatus()
                .isOk();

        verify(facade).findPoints("test", Coordinates.ZERO);
    }

    @Test
    void itFailsWhenPositionIsInvalid() {
        webTestClient
                .get()
                .uri(b -> b
                        .path("/find")
                        .queryParam("name", "test")
                        .queryParam("position", "blah, blah")
                        .build()
                )
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}