package com.taxi.DispatchSimulator.geo;

public class GeoDistance {

    /**
     * GeoPoint distance related methods
     * @Creator Jiahui Lu
     * @Time 2018.07.18
     * @Other
     * **/

    /** Return the distance of the GeoPoint in unit is km **/
    public static double calculate(double lat1, double lat2,
                                   double lon1, double lon2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                   + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                   * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    /**
     * Return the distance of the GeoPoint in unit is km
     * with parameter of two geoPoint
     * **/
    public static double calculate(GeoPoint p1,
                                   GeoPoint p2) {
        return calculate(p1.getLat(), p2.getLat(),
                         p1.getLon(), p2.getLon());
    }

    /**
     * Equation for converse km and latitude:
     * one degree latitude = 111.3195 km
     * **/
    public static double kmToLatitude(double deltaLat) { return deltaLat / 111.3195; }
    public static double latitudeToKm(double lat) { return lat * 111.3195; }
    /**
     * Approx Equation for converse km and latitude:
     * one degree longitude = 111.3195 km * cos(latitude)
     * **/
    public static double kmToLongitude(double deltaLon, double lat) { return deltaLon / (111.3195 * Math.cos(lat)); }
    public static double longitudeToKm(double lon, double lat) { return lon * 111.3195 * Math.cos(lat); }
}
