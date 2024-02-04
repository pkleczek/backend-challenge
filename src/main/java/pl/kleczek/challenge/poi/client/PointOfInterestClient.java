package pl.kleczek.challenge.poi.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class PointOfInterestClient {
    private final WebClient webClient;
    private final PointOfInterestClientProperties properties;

    public PointOfInterestClient(WebClient webClient, PointOfInterestClientProperties properties) {
        this.webClient = webClient;
        this.properties = properties;
    }

    public Flux<ExternalPointOfInterest> getAll() {
        return webClient.get().uri(properties.getUrl())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ExternalPointOfInterestResponse.class)
                .flatMapIterable(ExternalPointOfInterestResponse::places)
                .filter(p -> p.getName() != null && p.getCoordinates() != null);
    }
}
