package pl.kleczek.challenge.poi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class PoiConfiguration {
    @Bean
    PointOfInterestFacade pointOfInterestFacade(PointOfInterestRepository pointOfInterestRepository) {
        return new PointOfInterestFacade(pointOfInterestRepository);
    }
}
