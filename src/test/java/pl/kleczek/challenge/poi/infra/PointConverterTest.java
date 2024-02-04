package pl.kleczek.challenge.poi.infra;

import org.junit.jupiter.api.Test;
import pl.kleczek.challenge.poi.CoordinatesFixtures;
import pl.kleczek.challenge.poi.PointOfInterestWithAddress;
import pl.kleczek.challenge.poi.client.ExternalPointOfInterest;

import static org.assertj.core.api.Assertions.assertThat;

class PointConverterTest {
    @Test
    void itShouldHandlePointWithSeparateAddressFields() {
        ExternalPointOfInterest externalPointOfInterest = aPointWithAddressSeparated();

        PointOfInterestWithAddress converted = PointConverter.convert(externalPointOfInterest);

        assertThat(converted)
                .extracting(PointOfInterestWithAddress::name, PointOfInterestWithAddress::address)
                .containsExactly("A place", "Some street 7, 12345 New York");
    }

    @Test
    void itShouldHandlePointWithCommaAddressField() {
        ExternalPointOfInterest externalPointOfInterest = aPointWithAddressCombined();

        PointOfInterestWithAddress converted = PointConverter.convert(externalPointOfInterest);

        assertThat(converted)
                .extracting(PointOfInterestWithAddress::name, PointOfInterestWithAddress::address)
                .containsExactly("Another place", "Another street 13, 54321 Warsaw");
    }

    @Test
    void itShouldHandlePointWithUnknownStructure() {
        ExternalPointOfInterest externalPointOfInterest = aPointWithAUnknownStructure();

        PointOfInterestWithAddress converted = PointConverter.convert(externalPointOfInterest);

        assertThat(converted)
                .extracting(PointOfInterestWithAddress::name, PointOfInterestWithAddress::address)
                .containsExactly("Test test", "Test test");
    }

    private static ExternalPointOfInterest aPointWithAddressSeparated() {
        ExternalPointOfInterest point = new ExternalPointOfInterest();
        point.setName("A place");
        point.setCity("New York");
        point.setStreet("Some street 7");
        point.setZipCode("12345");
        point.setCoordinates(CoordinatesFixtures.NEW_YORK);
        return point;
    }

    private static ExternalPointOfInterest aPointWithAddressCombined() {
        ExternalPointOfInterest point = new ExternalPointOfInterest();
        point.setName("Another place, Another street 13, 54321 Warsaw");
        point.setCoordinates(CoordinatesFixtures.WARSAW);
        return point;
    }

    private ExternalPointOfInterest aPointWithAUnknownStructure() {
        ExternalPointOfInterest point = new ExternalPointOfInterest();
        point.setName("Test test");
        point.setCoordinates(CoordinatesFixtures.KRAKOW);
        return point;
    }
}