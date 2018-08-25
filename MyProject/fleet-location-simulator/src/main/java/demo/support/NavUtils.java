/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package demo.support;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import demo.model.Point;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Navigation utility functions.
 */
public class NavUtils {

    private static double EARTH_RADIUS_IN_METERS = DistanceUtils.EARTH_MEAN_RADIUS_KM * 1000;

    /**
     * Private Constructor.
     * Suppress default constructor for non-instantiability
     */
    private NavUtils() {
        throw new AssertionError();
    }

    /**
     * Returns distance (in meters) between 2 points.
     *
     * @param point1 Must not be null
     * @param point2 Must not be null
     * @return distance in meters
     */
    public static double getDistance(Point point1, Point point2) {
        Assert.notNull(point1, "point1 must not be null");
        Assert.notNull(point2, "point2 must not be null");

        final SpatialContext ctx = SpatialContext.GEO;
        com.spatial4j.core.shape.Point p1 = ctx.makePoint(point1.getLongitude(), point1.getLatitude());
        com.spatial4j.core.shape.Point p2 = ctx.makePoint(point2.getLongitude(), point2.getLatitude());

        return DistanceUtils.degrees2Dist(ctx.getDistCalc().distance(p1, p2), DistanceUtils.EARTH_MEAN_RADIUS_KM) * 1000;
    }

    public static double getTotalDistance(List<Point> points) {

        double totalDistance = 0;

        int count = 0;
        Point previousCount = null;

        for (Point point : points) {
            count++;

            if (count > 1 && count < points.size()) {
                totalDistance = totalDistance + getDistance(previousCount, point);
            }

            previousCount = point;
        }

        return totalDistance;
    }

    /**
     * Returns bearing of position 2 from position 1.
     *
     * @param pt1
     * @param pt2
     * @return
     */
    public static double getBearing(Point pt1, Point pt2) {
        double longitude1 = pt1.getLongitude();
        double longitude2 = pt2.getLongitude();
        double latitude1 = Math.toRadians(pt1.getLatitude());
        double latitude2 = Math.toRadians(pt2.getLatitude());
        double longDiff = Math.toRadians(longitude2 - longitude1);
        double y = Math.sin(longDiff) * Math.cos(latitude2);
        double x = Math.cos(latitude1) * Math.sin(latitude2) - Math.sin(latitude1) * Math.cos(latitude2) * Math.cos(longDiff);
        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }

    /**
     * Returns coordinates of position which is given distance and bearing from given point.
     *
     * @param pt1
     * @param dist
     * @param brg
     * @return
     */
    public static Point getPosition(Point pt1, double d, double brg) {
        if (Double.doubleToRawLongBits(d) == 0) {
            return pt1;
        }

        double lat1 = Math.toRadians(pt1.getLatitude());
        double lon1 = Math.toRadians(pt1.getLongitude());
        double brgAsRadians = Math.toRadians(brg);

        double lat2 = Math.asin(Math.sin(lat1) * Math.cos(d / EARTH_RADIUS_IN_METERS) + Math.cos(lat1) * Math.sin(d / EARTH_RADIUS_IN_METERS) * Math.cos(brgAsRadians));
        double x = Math.sin(brgAsRadians) * Math.sin(d / EARTH_RADIUS_IN_METERS) * Math.cos(lat1);
        double y = Math.cos(d / EARTH_RADIUS_IN_METERS) - Math.sin(lat1) * Math.sin(lat2);
        double lon2 = lon1 + Math.atan2(x, y);

        return new Point(Math.toDegrees(lat2), Math.toDegrees(lon2));

    }

    public static List<Point> decodePolyline(String polyline) {
        final List<LatLng> latLngs = PolylineEncoding.decode(polyline);
        return latLngs.stream().map(latLng -> new Point(latLng.lat, latLng.lng))
                .collect(Collectors.toList());
    }

    public static String encodePolyline(List<Point> points) {
        final List<LatLng> latLngs = points.stream().map(point -> new LatLng(point.getLatitude(), point.getLongitude()))
                .collect(Collectors.toList());
        return PolylineEncoding.encode(latLngs);
    }

    public static Point getLookAtPoint(List<Point> points) {
        double accumulatedLatitude = 0;
        double accumulatedLongitude = 0;

        for (Point point : points) {
            accumulatedLatitude += point.getLatitude();
            accumulatedLongitude += point.getLongitude();
        }
        return new Point(accumulatedLatitude / points.size(), accumulatedLongitude / points.size());
    }
}
