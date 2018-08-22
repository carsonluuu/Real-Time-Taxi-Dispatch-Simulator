package com.taxi.DispatchSimulator.map;

import com.taxi.DispatchSimulator.object.Driver;
import com.taxi.DispatchSimulator.geo.GeoPoint;

import java.util.*;

public class DriverMap {
    /**
     * @Discription Generate the drivers Map with the given driver list
     * @Parameter The driver list
     * @Creator Jiahui Lu
     * @Time 2018.07.16
     * @Other
     */

    /** The mapping with GeoPoints with drivers in stage **/
    public static Map<String, Set<Driver>> driverMap;
    public static Set<Driver> driverList;

    public DriverMap(int numOfDrivers) {
        driverMap = new HashMap<>();

        /** Get driverList in stage **/
        DriverInit DI = new DriverInit(numOfDrivers);
        driverList = DI.getInitialDrivers();

        /** One GeoPoint may have multiple drivers in stage **/
        for (Driver driver : driverList) {
            GeoPoint driverPos = GeoPoint.setPrecision(driver.getDriverLocation(), 3);
            if (!driverMap.containsKey(driverPos.toGeoHash())) {
                driverMap.put(driverPos.toGeoHash(), new HashSet<>());
            }
            driverMap.get(driverPos.toGeoHash()).add(driver);
        }
    }

    /** Get the Driver Map **/
    public static Map<String, Set<Driver>> getDriverMap() { return driverMap; }
    /** Get the list of the generated drivers **/
    public static Set<Driver> getDriverList() { return driverList; }
    /**
     * Create the grid for the map within the given range
     * eg: The grid length is 110 * 900 with 99000 blocks under this precision
     */
    public GeoPoint[][] getGridMap() {
        /** Loading the map settings...**/
        MapSettings setting = new MapSettings();
        double scale = setting.getPrecision();
        double[] size = setting.getSize();
        double LONGITUDE_MIN = size[0];
        double LONGITUDE_MAX = size[1];
        double LATITUDE_MIN  = size[2];
        double LATITUDE_MAX  = size[3];

        /**
         * A example:
         * lonScope: (104.11 - 104.00) / 0.001 = 110
         * latScope: (31.0 - 30.1) / 0.001 = 900
         * **/
        int lonScope = (int)((LONGITUDE_MAX - LONGITUDE_MIN) / scale);
        int latScope = (int)((LATITUDE_MAX - LATITUDE_MIN) / scale);
        GeoPoint[][] grid = new GeoPoint[lonScope][latScope];
        for (int lon = 0; lon < grid.length; lon++) {
            for (int lat = 0; lat < grid[0].length; lat++) {
                grid[lon][lat] = new GeoPoint(LONGITUDE_MIN + lon * scale,
                                               LATITUDE_MIN + lat * scale);
            }
        }

        return grid;
    }
}
