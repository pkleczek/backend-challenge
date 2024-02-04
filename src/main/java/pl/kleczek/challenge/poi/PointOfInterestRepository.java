package pl.kleczek.challenge.poi;

import reactor.core.publisher.Flux;

public interface PointOfInterestRepository {
    Flux<PointOfInterestWithAddress> getAllPoints();
}
