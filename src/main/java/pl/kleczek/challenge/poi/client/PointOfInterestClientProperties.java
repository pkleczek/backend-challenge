package pl.kleczek.challenge.poi.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
@ConfigurationProperties("application.poi")
public class PointOfInterestClientProperties {
    private URI url;

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }
}
