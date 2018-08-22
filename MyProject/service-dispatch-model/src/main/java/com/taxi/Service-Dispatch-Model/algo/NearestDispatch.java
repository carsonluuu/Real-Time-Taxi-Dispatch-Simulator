package com.taxi.DispatchSimulator.algo;

import com.taxi.DispatchSimulator.geo.GeoDistance;
import com.taxi.DispatchSimulator.geo.GeoHashUtils;
import com.taxi.DispatchSimulator.geo.GeoPoint;
import com.taxi.DispatchSimulator.object.Driver;

import java.util.*;

public class NearestDispatch {
    /**
     * The BFS level for the current use
     * For the nth level: the total geoHash grid is 4 * (n + 1) where n > 1
     * **/
    private static int level = 0;

    /** Distance for the driver to user **/
    private static double distance;

//    /** geoHash for the target driver **/
//    private static String targetGeoHash;

    private static List<Set<Driver>> BFS(GeoPoint userPos, Map<String, Set<Driver>> map) {
        /**
         * Apply BFS blindly search, finding all the geoHash grids with the same level
         * that have driver(s)
         * @Parameter: user position, driver map
         * @Return: the GeoHash List of all the grids that have driver(s)
         * */
        List<Set<Driver>> res = new ArrayList<>();

        String userGeoHash = userPos.toGeoHash();
        Queue<String> q = new LinkedList<>();
        Set<String> set = new HashSet<>();

        q.offer(userGeoHash);
        set.add(userGeoHash);
        // Early termination when user position has driver(s)
        if (map.containsKey(userGeoHash)) {
            res.add(map.get(userGeoHash));
            return res;
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (map.get(curr) != null && map.get(curr).size() > 0) {
                    /**
                     * For each potential driver, check if it is "on"
                     * If it is not "on", we did not add it into return res
                     * **/
                    Set<Driver> setCopy = new HashSet<>(map.get(curr));
                    Iterator<Driver> iterator = setCopy.iterator();
                    while (iterator.hasNext()) {
                        Driver potentialDriver = iterator.next();
                        if (!potentialDriver.getDriverStatus().equals("on")) {
                            iterator.remove();
                        }
                    }
                    if (setCopy.size() > 0) {
                        res.add(setCopy);
                    }
                }
                /** Add all GeoHashes of the cells next to a given geoHash to a list. **/
                List<String> neighborsList = new ArrayList<>();
                GeoHashUtils.addNeighbors(curr, neighborsList);
                for (String nei : neighborsList) {
                    if (set.contains(nei)) {
                        continue;
                    }
                    set.add(nei);
                    q.offer(nei);
                }
            }
            level++;
            if (res.size() > 0) {
                break; // This level has all the driver(s) in GeoHashes
            }
        }
        return res;
    }

    /** Find the closest driver among all the geoHash grids of drivers **/
    public static Driver dispatchDriver(GeoPoint userPos,
                                        Map<String, Set<Driver>> map) {
        distance = Double.MAX_VALUE;
        Driver resDriver = new Driver();
        List<Set<Driver>> res = BFS(userPos, map);
        for (int i = 0; i < res.size(); i++) {
            for (Driver d : res.get(i)) {
                double dis = GeoDistance.calculate(d.getDriverLocation(), userPos);
                if (distance > dis) {
                    resDriver = d;
                    distance = dis;
                }
            }
        }

        return resDriver;
    }

    /** Get the current level with all the geoHash grids with drivers **/
    public static int getLevel() { return level; }

    /** Find the closest driver's distance to user among all the geoHash grids of drivers **/
    public static double getDistance() { return distance; }

//    /** Find the closest driver's geoHash to user among all the geoHash grids of drivers **/
//    public static String getTagetGeoHash() { return targetGeoHash; }
}
