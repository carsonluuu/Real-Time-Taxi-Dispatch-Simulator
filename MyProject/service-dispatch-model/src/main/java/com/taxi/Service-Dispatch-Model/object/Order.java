package com.taxi.DispatchSimulator.object;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


import com.taxi.DispatchSimulator.geo.GeoPoint;

@Entity
@Table(name = "taxiorder")
public class Order {
    /**
     * <p>
     * Order class for the information of order, including orderID,
     * order start/end time and location, driver and user ID, distance and related instances
     * </p>
     * @author Jiahui Lu
     * @Time 2018.07.16
     */

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Integer id;
    private int orderStartT;
    private int orderEndT;
    private String orderStartL;
    private String orderEndL;
    private String driverID;
    private String userID;
    private double manhattanDistance;

    /** Manhattan distance related methods **/
    public double getManhattanDistance() { return manhattanDistance; }
    public void setManhattanDistance(double manhattanDistance) { this.manhattanDistance = manhattanDistance; }

    /** DriverID related methods **/
    public String getDriverID() { return driverID; }
    public void setDriverID(String driverID) { this.driverID = driverID; }

    /** UserID related methods **/
    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    /** Order ID related methods **/
    public Integer getOrderID() { return id; }
    public void setOrderID(Integer id) { this.id = id; }

    /** Order start and end time related methods **/
    public int getOrderStartT() { return orderStartT; }
    public void setOrderStartT(int orderStartT) { this.orderStartT = orderStartT; }
    public int getOrderEndT() { return orderEndT; }
    public void setOrderEndT(int orderEndT) { this.orderEndT = orderEndT; }

    /** Order start and end location related methods **/
    public String getOrderStartL() { return orderStartL; }
    public void setOrderStartL(String orderStartL) { this.orderStartL = orderStartL; }
    public String getOrderEndL() { return orderEndL; }
    public void setOrderEndL(String orderEndL) { this.orderEndL = orderEndL; }
}
