package pl.kleczek.challenge.poi.client;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import pl.kleczek.challenge.poi.Coordinates;

// due to current Jackson limitations this is not a java record
@Data
public class ExternalPointOfInterest {
    private String id;
    private String placeId;
    private String partnerId;
    @JsonUnwrapped
    private Coordinates coordinates;
    private String name;
    private String street;
    private String city;
    private String zipCode;
    private String countryCode;
}
