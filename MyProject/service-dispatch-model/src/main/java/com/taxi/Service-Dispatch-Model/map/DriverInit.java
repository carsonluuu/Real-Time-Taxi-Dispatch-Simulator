package com.taxi.DispatchSimulator.map;

import com.taxi.DispatchSimulator.object.Driver;
import com.taxi.DispatchSimulator.geo.GeoPoint;
import com.taxi.DispatchSimulator.utils.ReadDriverID;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DriverInit {
    /**
     * @Discription Generate the drivers will show in the map with the given number of cars given
     * @Parameter The number of cars with drivers initialized
     * @Creator Jiahui Lu
     * @Time 2018.07.16
     * @Other
     */

    /** The list of driver in stage **/
    private Set<Driver> driverList;

    public DriverInit(int numOfDrivers) {

        /** The scope of the map in longitude and latitude **/
        MapSettings setting = new MapSettings();
        double[] size = setting.getSize();

        double LONGITUDE_MIN = size[0];
        double LONGITUDE_MAX = size[1];
        double LATITUDE_MIN  = size[2];
        double LATITUDE_MAX  = size[3];

        ReadDriverID did = new ReadDriverID();
        List<String> driverIDList = did.getDriverIDList();

        driverList = new HashSet<>();
        /** Initialize drivers with random location within the scope of the stage **/
        for (int i = 0; i < numOfDrivers; i++) {
            Driver d = new Driver();
            GeoPoint location = new GeoPoint(Math.random() * (LONGITUDE_MAX - LONGITUDE_MIN) + LONGITUDE_MIN,
                                             Math.random() * (LATITUDE_MAX - LATITUDE_MIN) + LATITUDE_MIN);
            /** Set locations, status, ID and add to the driver list**/
            d.setDriverLocation(location);
            d.setDriverStatus("on");
            d.setDriverID(driverIDList.get(i));
            driverList.add(d);
        }
    }

    /** Get the list of the generated drivers **/
    public Set<Driver> getInitialDrivers() { return driverList; }
}
