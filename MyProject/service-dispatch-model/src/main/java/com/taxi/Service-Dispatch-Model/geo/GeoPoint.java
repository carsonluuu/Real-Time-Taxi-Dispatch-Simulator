package com.taxi.DispatchSimulator.geo;

import java.math.BigDecimal;

public final class GeoPoint {
    /**
     * Geo Points class with longitude and latitude
     * @author Jiahui Lu
     * @Time 2018.07.16
     */
    private double lon, lat;

    public GeoPoint() {
    }

    public GeoPoint(String value) {
        this.resetFromString(value);
    }

    public GeoPoint(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    /** Get longitude and latitude**/
    public final double getLat() { return this.lat; }
    public final double getLon() { return this.lon; }

    /** Reset longitude/latitude**/
    public GeoPoint reset(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        return this;
    }
    public GeoPoint resetLat(double lat) {
        this.lat = lat;
        return this;
    }
    public GeoPoint resetLon(double lon) {
        this.lon = lon;
        return this;
    }

    public String toGeoHash() {
        return GeoHashUtils.encode(lat, lon, 6);
    }

    public static GeoPoint setPrecision(GeoPoint pair, int precision) {
        double lon = pair.getLon(), lat = pair.getLat();
        BigDecimal LonBD = new BigDecimal(lon);
        BigDecimal LatBD = new BigDecimal(lat);
        lon = LonBD.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
        lat = LatBD.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();

        return new GeoPoint(lon, lat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoPoint geoPoint = (GeoPoint) o;

        if (Double.compare(geoPoint.lat, lat) != 0) return false;
        if (Double.compare(geoPoint.lon, lon) != 0) return false;

        return true;
    }

    @Override
    public String toString() {
        return lat + ", " + lon;
    }

    public GeoPoint resetFromString(String value) {
        int comma = value.indexOf(',');
        if (comma != -1) {
            lat = Double.parseDouble(value.substring(0, comma).trim());
            lon = Double.parseDouble(value.substring(comma + 1).trim());
        } else {
            resetFromGeoHash(value);
        }
        return this;
    }

    public GeoPoint resetFromGeoHash(String hash) {
        GeoHashUtils.decode(hash, this);
        return this;
    }

    public static GeoPoint parseFromLatLon(String latLon) {
        GeoPoint point = new GeoPoint();
        point.resetFromString(latLon);
        return point;
    }
}
