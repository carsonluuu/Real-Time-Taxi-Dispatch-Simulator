package com.taxi.DispatchSimulator.map;

import com.taxi.DispatchSimulator.geo.GeoDistance;
import com.taxi.DispatchSimulator.geo.GeoPoint;
import com.taxi.DispatchSimulator.object.Driver;

import java.util.*;

import static com.taxi.DispatchSimulator.map.DriverMap.driverList;

public class DriveMove {

    /** Average speed in a city is 7.5 m/s for example
     * Assuming a constant speed move
     *  Distance = speed * timeInterval
     * @Discription Simulate the random move of drivers
     * @Parameter The speed in "km/s", the timeInterval in "second"
     * @Creator Jiahui Lu
     * @Time 2018.07.17
     * @Other
     */

    /**
     * Update driver list with new location when called
     * Return the updated list
     * **/
    private static Set<Driver> updateDriver(Set<Driver> driverList, double speed, int timeInterval) {
        for (Driver d : driverList) {
            double degree = Math.random() * 360;
            double distance = speed * timeInterval / 1000;
            d.setDriverLocation(converseToGeo(d.getDriverLocation(),
                                distance, degree));
        }

        return driverList;
    }

    /**
     * Update driver map with new locations of drivers when called
     * Return the updated map
     * **/
    public static Map<String, Set<Driver>> updateMap(Map<String, Set<Driver>> driverMap,
                                                     Set<Driver> driverList,
                                                     double speed, int timeInterval) {
        driverMap.clear(); //clear the old map data
        updateDriver(driverList, speed, timeInterval);
        for (Driver driver : driverList) {
            GeoPoint driverPos = GeoPoint.setPrecision(driver.getDriverLocation(), 3);
            if (!driverMap.containsKey(driverPos.toGeoHash())) {
                driverMap.put(driverPos.toGeoHash(), new HashSet<>());
            }
            driverMap.get(driverPos.toGeoHash()).add(driver);
        }

        return driverMap;
    }

    /**
     * Converse and give a new GeoPoint with the random direction
     * @Parameter: Base GeoPoint, polar information
     * @Return: The new GeoPoint from the kilometers
     * **/
    private static GeoPoint converseToGeo(GeoPoint point,
                                   double distance, double degree) {
        double radian = Math.toRadians(degree);
        double deltaLon = distance * Math.sin(radian);
        double deltaLat = distance * Math.cos(radian);

        deltaLon = GeoDistance.kmToLongitude(deltaLon,
                                             Math.toRadians(point.getLon()));
        deltaLat = GeoDistance.kmToLatitude(deltaLat);

        return new GeoPoint(point.getLon() + deltaLon,
                            point.getLat() + deltaLat);
    }

}
