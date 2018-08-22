package com.taxi.DispatchSimulator.object;

import com.taxi.DispatchSimulator.geo.GeoPoint;

public class User {
    /**
     * <p>
     * User class for the information of users, including userID, status,
     * order start/end time and current/destination location, bearing and related instances
     * </p>
     * @author Jiahui Lu
     * @Time 2018.07.16
     */
    private String userID;
    private int startT;
    private int endT;
    private GeoPoint userLocation;
    private GeoPoint userAimLocation;
    private double userBearing;
    private String status;

    /** Bearing related methods **/
    public double getUserBearing() { return userBearing; }
    public void setUserBearing(double userBearing) { this.userBearing = userBearing; }

    /** Destination related methods **/
    public GeoPoint getUserAimLocation() { return userAimLocation; }
    public void setUserAimLocation(GeoPoint userAimLocation) { this.userAimLocation = userAimLocation; }

    /** User Status related methods **/
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    /** User/Order start/end time related methods **/
    public int getStartT() { return startT; }
    public void setStartT(int startT) { this.startT = startT; }
    public int getEndT() { return endT; }
    public void setEndT(int endT) { this.endT = endT; }

    /** UserID related methods **/
    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    /** User location related methods **/
    public GeoPoint getUserLocation() { return userLocation; }
    public void setUserLocation(GeoPoint userLocation) { this.userLocation = userLocation; }
}
