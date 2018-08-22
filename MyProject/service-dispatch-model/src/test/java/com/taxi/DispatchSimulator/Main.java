package com.taxi.DispatchSimulator;


import com.taxi.DispatchSimulator.algo.NearestDispatch;
import com.taxi.DispatchSimulator.algo.OptimalDispatch;
import com.taxi.DispatchSimulator.bussiness.StartOptimalDispatch;
import com.taxi.DispatchSimulator.geo.GeoDistance;
import com.taxi.DispatchSimulator.map.DriveMove;
import com.taxi.DispatchSimulator.map.DriverMap;
import com.taxi.DispatchSimulator.map.MapSettings;
import com.taxi.DispatchSimulator.object.Driver;
import com.taxi.DispatchSimulator.geo.GeoHashUtils;
import com.taxi.DispatchSimulator.geo.GeoPoint;
import com.taxi.DispatchSimulator.object.Order;
import com.taxi.DispatchSimulator.object.User;
import com.taxi.DispatchSimulator.utils.CreateOrder;
import com.taxi.DispatchSimulator.utils.ReadAcceptTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.taxi.DispatchSimulator.bussiness.StartDispatch.start;

public class Main {
    public static void main(String[] args) {
//        MapSettings settingMap = new MapSettings();
//        /** Map area: 100.11 * 10.5 **/
//        settingMap.setup(104.00, 104.11, 30.1, 31.0, 0.001);
//
//        DriverMap driverMap = new DriverMap(4000);
//        Map<String, Set<Driver>> map = driverMap.getDriverMap();
//        System.out.println(map.size());
//        for (String s : map.keySet()) {
//            System.out.print(s);
//            System.out.println(" NO." + " Drivers in this location: " + map.get(s).size());
//        }
//
//        NearestDispatch nd = new NearestDispatch();
//        Driver res = nd.dispatchDriver(new GeoPoint(104.0147, 30.6922), map);
//        System.out.println(res.getDriverID() + " " + res.getDriverLocation().toString());

//        System.out.println("----------New Location After 30 Seconds----------");
//
//        DriveMove dm = new DriveMove(0.0075, 36);
//        Map<String, List<Driver>> newMap = dm.updateMap();
//        for (String s : newMap.keySet()) {
//            System.out.print(s);
//            GeoPoint gp = new GeoPoint();
//            gp = gp.resetFromString(s);
//            System.out.print(" GeoHash: " + GeoHashUtils.encode(gp.getLat(), gp.getLon(), 6));
//            System.out.println(" NO." + newMap.get(s).get(0).getDriverID() + " Drivers in this location: " + newMap.get(s).size());
//        }
//
//        System.out.println("----------New Location After 60 Seconds----------");
//
//        newMap = dm.updateMap();
//        for (String s : newMap.keySet()) {
//            System.out.print(s);
//            GeoPoint gp = new GeoPoint();
//            gp = gp.resetFromString(s);
//            System.out.print(" GeoHash: " + GeoHashUtils.encode(gp.getLat(), gp.getLon(), 6));
//            System.out.println(" NO." + newMap.get(s).get(0).getDriverID() + " Drivers in this location: " + newMap.get(s).size());
//        }
//        System.out.println(GeoHashUtils.neighbors("wm3gre"));

//        MapSettings settingMap = new MapSettings();
//        /** Map area: 100.11 * 10.5 **/
//        settingMap.setup(104.00, 104.11, 30.1, 31.0, 0.001);
//
//        DriverMap driverMap = new DriverMap(20000);
//        Map<String, Set<Driver>> map = driverMap.getDriverMap();
//        Set<Driver> drivers = driverMap.getDriverList();
//        List<Order> orderList = start(drivers, map);
//        for (Order elem : orderList) {
//            CreateOrder.printOrder(elem);
//        }

        MapSettings settingMap = new MapSettings();
        /** Map area: 100.11 * 10.5 **/
        settingMap.setup(104.00, 104.11, 30.1, 31.0, 0.001);

        DriverMap driverMap = new DriverMap(20000);
        Map<String, Set<Driver>> map = driverMap.getDriverMap();
        Set<Driver> drivers = driverMap.getDriverList();

        Map<User, List<Driver>> res = StartOptimalDispatch.start(drivers, map);
        System.out.println();


    }
}
