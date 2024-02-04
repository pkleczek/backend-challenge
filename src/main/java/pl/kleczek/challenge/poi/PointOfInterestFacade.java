package pl.kleczek.challenge.poi;

import reactor.core.publisher.Flux;

import java.util.Comparator;

public class PointOfInterestFacade {
    private final PointOfInterestRepository pointOfInterestRepository;

    PointOfInterestFacade(PointOfInterestRepository pointOfInterestRepository) {
        this.pointOfInterestRepository = pointOfInterestRepository;
    }

    public Flux<PointOfInterest> findPoints(String name, Coordinates position) {
        String lowerCaseName = name.toLowerCase();
        return pointOfInterestRepository.getAllPoints()
                .filter(p -> p.getName().toLowerCase().contains(lowerCaseName))
                .map(p -> new PointOfInterest(p.getName(), p.getName(), position.distance(p.getCoordinates())))
                .sort(Comparator.comparing(PointOfInterest::distance));
    }
}
