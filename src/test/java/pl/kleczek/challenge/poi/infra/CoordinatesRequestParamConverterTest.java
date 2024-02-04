package pl.kleczek.challenge.poi.infra;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.kleczek.challenge.poi.Coordinates;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesRequestParamConverterTest {
    CoordinatesRequestParamConverter converter = new CoordinatesRequestParamConverter();

    @ParameterizedTest
    @ValueSource(strings = {"0.000000, 0.000000", "20.000000, 10.000000", "-33.333000, -44.444000"})
    void itHandlesCorrectCoordinatesString(String source) {
        Coordinates convert = converter.convert(source);

        assertEquals(source, convert.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.000000,", ",20.000000", "blah, blah", "", ","})
    void itThrowsExceptionOnInvalidString(String source) {
        var ex = assertThrows(IllegalArgumentException.class, () -> converter.convert(source));

        assertTrue(ex.getMessage().contains("coordinates"));
    }
}