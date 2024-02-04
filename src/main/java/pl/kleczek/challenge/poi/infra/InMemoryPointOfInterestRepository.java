package pl.kleczek.challenge.poi.infra;

import pl.kleczek.challenge.poi.Coordinates;
import pl.kleczek.challenge.poi.PointOfInterestRepository;
import pl.kleczek.challenge.poi.PointOfInterestWithAddress;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPointOfInterestRepository implements PointOfInterestRepository {
    private final List<PointOfInterestWithAddress> points = new ArrayList<>();

    public InMemoryPointOfInterestRepository setupPoint(Coordinates coordinates, String name) {
        PointOfInterestWithAddress pointOfInterestWithAddress = new PointOfInterestWithAddress(name, "", coordinates);
        points.add(pointOfInterestWithAddress);
        return this;
    }


    @Override
    public Flux<PointOfInterestWithAddress> getAllPoints() {
        return Flux.fromIterable(points);
    }
}
