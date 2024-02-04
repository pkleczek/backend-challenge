package pl.kleczek.challenge.poi.infra;

import org.springframework.stereotype.Repository;
import pl.kleczek.challenge.poi.PointOfInterestRepository;
import pl.kleczek.challenge.poi.client.ExternalPointOfInterest;
import pl.kleczek.challenge.poi.client.PointOfInterestClient;
import reactor.core.publisher.Flux;

@Repository
public class ExternalPoiRepository implements PointOfInterestRepository {
    private final PointOfInterestClient client;

    public ExternalPoiRepository(PointOfInterestClient client) {
        this.client = client;
    }
    
    @Override
    public Flux<ExternalPointOfInterest> getAllPoints() {
        return client.getAll();
    }
}
