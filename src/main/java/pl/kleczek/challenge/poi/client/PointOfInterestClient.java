package pl.kleczek.challenge.poi.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class PointOfInterestClient {
    private final WebClient webClient;
    private final PointOfInterestClientProperties properties;

    public Flux<ExternalPointOfInterest> getAll() {
        return webClient.get().uri(properties.getUrl())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ExternalPointOfInterestResponse.class)
                .flatMapIterable(ExternalPointOfInterestResponse::places);
    }
}
