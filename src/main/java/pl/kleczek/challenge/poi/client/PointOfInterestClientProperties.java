package pl.kleczek.challenge.poi.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Data
@Configuration
@ConfigurationProperties("application.poi")
public class PointOfInterestClientProperties {
    private URI url;
}
