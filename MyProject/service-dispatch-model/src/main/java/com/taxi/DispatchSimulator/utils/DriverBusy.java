package com.taxi.DispatchSimulator.utils;

import com.taxi.DispatchSimulator.object.Driver;

public class DriverBusy {
    /**
     * Set driver status to "busy"
     * @Creator Jiahui Lu
     * @Time 2018.07.19
     * @Other
     * **/
    public static void driverBusy(Driver assignedDriver,
                                  int currTime, int estimateFinishTime) {
        assignedDriver.setDriverStatus("busy");
        assignedDriver.setOnRoadT(currTime);
        assignedDriver.setOffRoadT(estimateFinishTime);
    }
}
