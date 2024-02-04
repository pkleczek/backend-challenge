package pl.kleczek.challenge.poi;

import pl.kleczek.challenge.poi.client.ExternalPointOfInterest;
import reactor.core.publisher.Flux;

public interface PointOfInterestRepository {
    Flux<ExternalPointOfInterest> getAllPoints();
}
