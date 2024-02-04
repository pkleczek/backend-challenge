package pl.kleczek.challenge.poi.infra;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.kleczek.challenge.poi.Coordinates;

@Component
class CoordinatesRequestParamConverter implements Converter<String, Coordinates> {
    @Override
    public Coordinates convert(String source) {
        String[] coordinatesArray = source.split(",");
        if (coordinatesArray.length != 2) {
            throw new IllegalArgumentException("Invalid coordinates format");
        }

        try {
            double latitude = Double.parseDouble(coordinatesArray[0].trim());
            double longitude = Double.parseDouble(coordinatesArray[1].trim());
            return new Coordinates(latitude, longitude);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid coordinates values");
        }
    }
}
