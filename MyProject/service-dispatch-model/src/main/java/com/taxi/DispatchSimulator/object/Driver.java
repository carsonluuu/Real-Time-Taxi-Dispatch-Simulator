package com.taxi.DispatchSimulator.object;

import com.taxi.DispatchSimulator.geo.GeoPoint;

public class Driver {
    /**
     * <p>
     * Driver class for the information of driver, including locations,
     * status, on road time, aim, bearing and related instances
     * </p>
     * @author Jiahui Lu
     * @Time 2018.07.16
     */
    private String driverID;
    private GeoPoint driverLocation;
    private String driverStatus;
    private int onRoadT;
    private int offRoadT;
    private int idleTime;
    private double aim;
    private double bearing;

    /** Bearing related methods **/
    public double getBearing() { return bearing; }
    public void setBearing(double bearing) { this.bearing = bearing; }

    /** DriverID related methods **/
    public String getDriverID() { return driverID; }
    public void setDriverID(String driverID) { this.driverID = driverID; }

    /** Driver Location related methods **/
    public GeoPoint getDriverLocation() { return driverLocation; }
    public void setDriverLocation(GeoPoint driverLocation) { this.driverLocation = driverLocation; }

    /** Driver status related methods **/
    public String getDriverStatus() { return driverStatus; }
    public void setDriverStatus(String driverStatus) { this.driverStatus = driverStatus; }

    /** Driver Road Time related methods **/
    public int getOnRoadT() { return onRoadT; }
    public void setOnRoadT(int onRoadT) { this.onRoadT = onRoadT; }
    public int getOffRoadT() { return offRoadT; }
    public void setOffRoadT(int offRoadT) { this.offRoadT = offRoadT; }

    /** Driver Destination related methods **/
    public double getAim() { return aim; }
    public void setAim(double aim) { this.aim = aim; }
}
