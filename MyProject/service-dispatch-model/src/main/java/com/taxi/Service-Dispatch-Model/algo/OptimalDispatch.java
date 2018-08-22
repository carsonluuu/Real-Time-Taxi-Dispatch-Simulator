package com.taxi.DispatchSimulator.algo;

import com.taxi.DispatchSimulator.geo.GeoDistance;
import com.taxi.DispatchSimulator.geo.GeoPoint;
import com.taxi.DispatchSimulator.object.Driver;
import com.taxi.DispatchSimulator.object.User;
import com.taxi.DispatchSimulator.utils.ReadAcceptTable;


import java.util.*;

public class OptimalDispatch {

    public static Map<User, List<Driver>> dispatchDrivers(Set<Driver> driverInRange, List<User> usersInRange) {
        List<Driver> driversInRange = new ArrayList<>();
        driversInRange.addAll(driverInRange);
        Map<User, List<Driver>> res = new HashMap<>();
        ReadAcceptTable readAcceptTable = new ReadAcceptTable();
        Map<String, Map<String, Double>> acceptRateTable = readAcceptTable.getAcceptRateTable();

        double[][] nums = new double[driversInRange.size()][usersInRange.size()];
        for (int i = 0; i < usersInRange.size(); i++) {
            GeoPoint departure = usersInRange.get(i).getUserLocation();
            GeoPoint destination = usersInRange.get(i).getUserAimLocation();
            // Feature one -> dist
            double dist = GeoDistance.calculate(departure, destination);
            int distNum = distToNum(dist);
            // Feature Two -> hour
            long unix = (long)usersInRange.get(i).getStartT();
            int hour = unixToHour(unix);
            int hourNum = hourToNum(hour);
            String featureCombination = distNum + " " + hour;
            for (int j = 0; j < driversInRange.size(); j++) {
                nums[j][i] = acceptRateTable.get(driversInRange.get(j).getDriverID()).get(featureCombination);
            }
        }

        List<Integer> init = new ArrayList<>();
        for (int k = 0; k < 5; k++) {
            climbing(nums, init);
        }

        for (int k = 0; k < init.size(); k++) {
            User temp = usersInRange.get(init.get(k));
            if (!res.containsKey(temp)) {
                res.put(temp, new ArrayList<>());
            }
            res.get(temp).add(driversInRange.get(k));
        }

        return res;

    }

    private static int hourToNum(int hour) {
        int res;
        if (7 <= hour && hour <= 9) {
            res = 0;
        } else if (18 <= hour && hour <= 20) {
            res = 1;
        } else if (3 <= hour && hour <= 6) {
            res = 3;
        } else if (13 <= hour && hour <= 17){
            res = 4;
        } else if (10 <= hour && hour <= 12) {
            res = 5;
        } else {
            res = 2;
        }

        return res;
    }

    private static int distToNum(double dist) {
        int res;
        if (dist <= 5) {
            res = 0;
        } else if (5 < dist && dist <= 10) {
            res = 1;
        } else if (10 < dist && dist <= 30) {
            res = 2;
        } else {
            res = 3;
        }

        return res;
    }

    public static int unixToHour(long ts) {
        Calendar calendar = Calendar.getInstance();
//        TimeZone tz = TimeZone.getTimeZone("GMT+:08:00");
//        calendar.setTimeZone(tz);
        calendar.setTimeInMillis(ts*1000);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static List<Integer> climbing(double[][] nums, List<Integer> init) {
        int m = nums.length;
        int n = nums[0].length;
        //List<Integer> init = new ArrayList<>();

        if (init == null || init.size() == 0) {
            for (double[] num : nums) {
                double res = 0;
                int index = 0;
                for (int i = 0; i < num.length; i++) {
                    if (num[i] > res) {
                        res = num[i];
                        index = i;
                    }
                }
                init.add(index);
            }
        }
        List<Double> E = new ArrayList<>();

        List<List<Double>> matching = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            double sum = 0;
            int cnt = 0;
            matching.add(new ArrayList<>());
            for (int i = 0; i < m; i++) {
                if (init.get(i) == j) {
                    matching.get(j).add(nums[i][j]);
                }
            }
            E.add(average(matching.get(j)));
        }

        double E0 = average(E);
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                int order = init.get(i);
                if (order != j) {
                    List<Double> tempE = new ArrayList<>(E);
                    tempE.set(j, addAvg(matching.get(j), nums[i][order]));
                    tempE.set(order, minusAvg(matching.get(order), nums[i][order]));
//                    System.out.print(average(tempE));
//                    System.out.print(" ");
//                    System.out.println(average(E));
                    if (average(tempE) > average(E)) {
                        E = tempE;
                        init.set(i, j);
                        //System.out.println(average(tempE));
                    }
                }
            }
        }

        System.out.println(E);
        System.out.println(average(E));
        return init;
    }

    private static double addAvg(List<Double> orderScore, double newScore) {
        double sum = 0;
        for (double elem : orderScore) {
            sum += elem;
        }

        return (sum + newScore) / (orderScore.size() + 1);
    }


    private static double minusAvg(List<Double> orderScore, double newScore) {
        double sum = 0;

        if (orderScore.size() <= 1) {
            return 0.0;
        }

        for (double elem : orderScore) {
            sum += elem;
        }

        return (sum - newScore) / (orderScore.size() - 1);
    }

    private static double average(List<Double> orderScore) {
        if (orderScore.size() == 0) {
            return 0.0;
        }

        double sum = 0;
        for (double elem : orderScore) {
            sum += elem;
        }

        return sum / orderScore.size();
    }

}
