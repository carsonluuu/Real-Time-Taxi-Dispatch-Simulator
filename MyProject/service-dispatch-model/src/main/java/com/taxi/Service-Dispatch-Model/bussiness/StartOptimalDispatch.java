package com.taxi.DispatchSimulator.bussiness;

import com.taxi.DispatchSimulator.algo.OptimalDispatch;
import com.taxi.DispatchSimulator.map.DriveMove;
import com.taxi.DispatchSimulator.object.Driver;
import com.taxi.DispatchSimulator.object.Order;
import com.taxi.DispatchSimulator.object.User;
import com.taxi.DispatchSimulator.utils.ReadUserRequest;

import java.util.*;

public class StartOptimalDispatch {

    public static Map<User, List<Driver>> start(Set<Driver> drivers, Map<String, Set<Driver>> map) {

        Map<User, List<Driver>> result = new HashMap<>();

        ReadUserRequest readUsers = new ReadUserRequest(100);
        Queue<User> userQueue = readUsers.getUserQueue();
        PriorityQueue<Driver> busyDriver = new PriorityQueue<>((d1, d2)-> d1.getOffRoadT() - d2.getOffRoadT());
        double SPEED = 7.5;
        Integer oid = 1;
        int originalTime = userQueue.peek().getStartT();
        int currTime = originalTime;
        int prevTime = originalTime;

        String currGeoHash = "";

        Map<String, List<User>> stackUserMap = new HashMap<>();
        Map<String, Integer> stackStartTime = new HashMap<>();

        while (!userQueue.isEmpty()) {
            if (userQueue.peek().getStartT() == currTime) {
                User currUser = userQueue.poll();

                // Updating the map when new user is assigned
                DriveMove.updateMap(map, drivers, SPEED, currTime - prevTime);
                // Check status
                CheckDriverStatus.check(currTime, busyDriver);

                currGeoHash = currUser.getUserLocation().toGeoHash();
                if (!stackUserMap.containsKey(currGeoHash)) {
                    stackUserMap.put(currGeoHash, new ArrayList<>());
                    stackStartTime.put(currGeoHash, currTime);
                }
                stackUserMap.get(currGeoHash).add(currUser);
                if (stackUserMap.get(currGeoHash).size() == 3 ||
                        currTime - stackStartTime.get(currGeoHash) > 200) {
                    Set<Driver> driversInRange = map.get(currGeoHash);
                    List<User> usersInRange = stackUserMap.get(currGeoHash);

                    Map<User, List<Driver>> res = OptimalDispatch.dispatchDrivers(driversInRange, usersInRange);

                    for (User elem : res.keySet()) {
                        result.put(elem, res.get(elem));
                    }

                    stackUserMap.remove(currGeoHash);
                    stackStartTime.remove(currGeoHash);
                }
                prevTime = currTime;
            } else {
                currTime++;
            }

        }

        return result;
    }
}
