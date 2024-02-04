package pl.kleczek.challenge.poi.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kleczek.challenge.poi.PointOfInterestRepository;
import pl.kleczek.challenge.poi.PointOfInterestWithAddress;
import pl.kleczek.challenge.poi.client.PointOfInterestClient;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class ExternalPoiRepository implements PointOfInterestRepository {
    private final PointOfInterestClient client;
    
    @Override
    public Flux<PointOfInterestWithAddress> getAllPoints() {
        return client.getAll()
                .map(PointConverter::convert);
    }
}
