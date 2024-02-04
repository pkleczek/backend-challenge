package pl.kleczek.challenge.poi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class PointOfInterestFacade {
    private final PointOfInterestRepository pointOfInterestRepository;

    public Flux<PointOfInterest> findPoints(String name, Coordinates position) {
        String lowerCaseName = name.toLowerCase();
        return pointOfInterestRepository.getAllPoints()
                .filter(p -> p.name().toLowerCase().contains(lowerCaseName))
                .map(p -> new PointOfInterest(p.name(), p.address(), position.distance(p.coordinates())))
                .sort(Comparator.comparing(PointOfInterest::distance));
    }
}
