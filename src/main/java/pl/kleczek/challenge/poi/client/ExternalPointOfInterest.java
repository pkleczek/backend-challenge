package pl.kleczek.challenge.poi.client;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import pl.kleczek.challenge.poi.Coordinates;

// due to current Jackson limitations this is not a java record
public class ExternalPointOfInterest {
    private Long id;
    @JsonUnwrapped
    private Coordinates coordinates;
    private String name;

    public ExternalPointOfInterest() {
    }

    public ExternalPointOfInterest(Long id, Coordinates coordinates, String name) {
        this.id = id;
        this.coordinates = coordinates;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
