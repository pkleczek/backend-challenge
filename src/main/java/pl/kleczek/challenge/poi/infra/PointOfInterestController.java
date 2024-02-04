package pl.kleczek.challenge.poi.infra;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kleczek.challenge.poi.Coordinates;
import pl.kleczek.challenge.poi.PointOfInterest;
import pl.kleczek.challenge.poi.PointOfInterestFacade;
import reactor.core.publisher.Flux;

import java.util.Optional;

@RestController
class PointOfInterestController {
    private final PointOfInterestFacade pointOfInterestFacade;

    PointOfInterestController(PointOfInterestFacade pointOfInterestFacade) {
        this.pointOfInterestFacade = pointOfInterestFacade;
    }

    @GetMapping("/find")
    Flux<PointOfInterest> findPoints(@RequestParam String name, @RequestParam(required = false) Optional<Coordinates> position) {
        return pointOfInterestFacade.findPoints(name, position.orElse(Coordinates.ZERO));
    }
}
