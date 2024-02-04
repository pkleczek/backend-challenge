package pl.kleczek.challenge.poi.infra;

import lombok.extern.slf4j.Slf4j;
import pl.kleczek.challenge.poi.PointOfInterestWithAddress;
import pl.kleczek.challenge.poi.client.ExternalPointOfInterest;

@Slf4j
class PointConverter {
    private PointConverter() {
    }

    public static PointOfInterestWithAddress convert(ExternalPointOfInterest external) {
        if (external.getCity() != null) {
            String address = "%s, %s %s".formatted(external.getStreet(), external.getZipCode(), external.getCity());
            return new PointOfInterestWithAddress(external.getName(), address, external.getCoordinates());
        } else {
            String[] split = external.getName().split(",", 2);
            if (split.length == 2) {
                return new PointOfInterestWithAddress(split[0].trim(), split[1].trim(), external.getCoordinates());
            } else {
                log.warn("Unrecognized structure of point:{} (id={}, placeId={}, partnerId={})", external.getName(), external.getId(), external.getPlaceId(), external.getPartnerId());
                return new PointOfInterestWithAddress(external.getName(), external.getName(), external.getCoordinates());
            }
        }
    }
}
