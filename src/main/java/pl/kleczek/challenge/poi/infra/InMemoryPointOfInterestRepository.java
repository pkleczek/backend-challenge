package pl.kleczek.challenge.poi.infra;

import pl.kleczek.challenge.poi.Coordinates;
import pl.kleczek.challenge.poi.PointOfInterestRepository;
import pl.kleczek.challenge.poi.client.ExternalPointOfInterest;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryPointOfInterestRepository implements PointOfInterestRepository {
    private final AtomicLong counter = new AtomicLong(0);
    private final List<ExternalPointOfInterest> pointOfInterests = new ArrayList<>();

    public InMemoryPointOfInterestRepository setupPoint(Coordinates coordinates, String name) {
        pointOfInterests.add(new ExternalPointOfInterest(counter.incrementAndGet(), coordinates, name));
        return this;
    }


    @Override
    public Flux<ExternalPointOfInterest> getAllPoints() {
        return Flux.fromIterable(pointOfInterests);
    }
}
