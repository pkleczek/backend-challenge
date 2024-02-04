package pl.kleczek.challenge.poi;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Coordinates(@JsonAlias("lat") double latitude, @JsonAlias("lon") double longitude) {
    private static final double EARTH_RADIUS_KM = 6371;
    public static final Coordinates ZERO = new Coordinates(0, 0);


    @Override
    public String toString() {
        return "%f, %f".formatted(latitude, longitude);
    }

    /**
     * Calculate distance between two points.
     * Adapted from <a href="https://www.geeksforgeeks.org/program-distance-two-points-earth/">GeeksforGeeks</a>
     *
     * @param another point to calculate the distance
     * @return distance in kilometers
     */
    public double distance(Coordinates another) {
        double lon1 = Math.toRadians(longitude);
        double lon2 = Math.toRadians(another.longitude);
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(another.latitude);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        return (c * EARTH_RADIUS_KM);
    }
}
