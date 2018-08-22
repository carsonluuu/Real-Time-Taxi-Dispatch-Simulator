package com.taxi.DispatchSimulator.bussiness;

import com.taxi.DispatchSimulator.object.Driver;

import java.util.PriorityQueue;

public class CheckDriverStatus {
    /**
     * This class will check driver status
     * Min heap will pop out the finished drivers with status reset
     * @parameter current time, priority queue for the busy driver
     * @Creator Jiahui Lu
     * @Time 2018.07.19
     * @Other
     * **/
    public static void check(int currTime, PriorityQueue<Driver> busyDriver) {
        while (!busyDriver.isEmpty() &&
                busyDriver.peek().getOffRoadT() <= currTime) {
            Driver curr = busyDriver.poll();
            curr.setDriverStatus("on");
            curr.setOnRoadT(0);
            curr.setOffRoadT(0);
        }
    }
}
