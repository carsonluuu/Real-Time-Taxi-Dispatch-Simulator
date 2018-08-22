package com.taxi.DispatchSimulator.bussiness;

import com.taxi.DispatchSimulator.algo.NearestDispatch;
import com.taxi.DispatchSimulator.geo.GeoDistance;
import com.taxi.DispatchSimulator.map.DriveMove;
import com.taxi.DispatchSimulator.map.DriverMap;
import com.taxi.DispatchSimulator.map.MapSettings;
import com.taxi.DispatchSimulator.object.Driver;
import com.taxi.DispatchSimulator.object.Order;
import com.taxi.DispatchSimulator.object.User;
import com.taxi.DispatchSimulator.utils.CreateOrder;
import com.taxi.DispatchSimulator.utils.DriverBusy;
import com.taxi.DispatchSimulator.utils.ReadUserRequest;
import com.taxi.DispatchSimulator.utils.WriteToCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.taxi.DispatchSimulator.utils.CreateOrder.createOrder;

public class StartDispatch {
    /**
     * @param map
     * @Creator Jiahui Lu
     * @Time 2018.07.18
     * @Other
     * Start driver dispatching followed by the order of the user queue
     * Record the orders
     * **/

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public static List<Order> start(Set<Driver> drivers, Map<String, Set<Driver>> map) {
        List<Order> orderList = new ArrayList<>();
        ReadUserRequest readUsers = new ReadUserRequest(100);
        Queue<User> userQueue = readUsers.getUserQueue();
        PriorityQueue<Driver> busyDriver = new PriorityQueue<>((d1, d2)-> d1.getOffRoadT() - d2.getOffRoadT());
        double SPEED = 7.5;
        Integer oid = 1;
        int originalTime = userQueue.peek().getStartT();
        int currTime = originalTime;
        int prevTime = originalTime;

        while (!userQueue.isEmpty()) {
            /** When the time matches for global time and user request time**/
            if (userQueue.peek().getStartT() == currTime) {
                User currUser = userQueue.poll();

                // Updating the map when new user is assigned
                map = DriveMove.updateMap(map, drivers, SPEED, currTime - prevTime);
                // Check status
                CheckDriverStatus.check(currTime, busyDriver);
                // Get the target Driver and the distance
                Driver assignedDriver = NearestDispatch.dispatchDriver(currUser.getUserLocation(), map);
                Double distanceToDriver = NearestDispatch.getDistance();

                // Get estimate trip time to get the estimate time for drivers to back to work
                double tripDistance = GeoDistance.calculate(currUser.getUserAimLocation(),
                        currUser.getUserLocation());
                int tripTime = (int) ((tripDistance / SPEED) * 1000); //get trip time in seconds
                int estimateFinishTime = currTime + tripTime;

                // Set assigned driver status
                DriverBusy.driverBusy(assignedDriver, currTime, estimateFinishTime);
                busyDriver.add(assignedDriver);
                // Print order info
                //orderList.add(CreateOrder.createOrder(oid, currUser, assignedDriver, distanceToDriver));
                //CreateOrder.printOrder(CreateOrder.createOrder(oid, currUser, assignedDriver, distanceToDriver));
                Order temp = CreateOrder.createOrder(oid, currUser, assignedDriver, distanceToDriver);

                orderList.add(temp);

                oid++;
                prevTime = currTime;
            } else {
                currTime++;
            }

//            if (oid % 200 == 0) {
//                double percetage = (oid / 209420.0) * 100;
//                System.out.println(percetage + "% finished");
//            }
        }

        WriteToCSV.writeCsvFile(orderList);

        return orderList;
    }

}
