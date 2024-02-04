package pl.kleczek.challenge.poi;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
@EnableWireMock({
        @ConfigureWireMock(name = "places-api", property = "places-client.url")
})
class PointOfInterestFacadeIntegrationTest {
    @InjectWireMock("places-api")
    WireMockServer wiremock;

    @Autowired
    PointOfInterestFacade facade;

    @ParameterizedTest
    @ValueSource(strings = {"payback", "aral", "vodafone"})
    void itReturnsMatchingPlaces(String name) throws IOException {
        setupWiremockResponse();

        Flux<PointOfInterest> pointOfInterest = facade.findPoints(name, Coordinates.ZERO);

        StepVerifier.create(pointOfInterest)
                .assertNext(p -> assertTrue(p.name().toLowerCase().startsWith(name)))
                .verifyComplete();
    }

    private void setupWiremockResponse() throws IOException {
        wiremock.stubFor(get(anyUrl())
                .willReturn(jsonResponse(testData(), OK.value())));
    }

    private static String testData() throws IOException {
        Path json = ResourceUtils.getFile("classpath:simple-response.json").toPath();
        return Files.readString(json);
    }
}