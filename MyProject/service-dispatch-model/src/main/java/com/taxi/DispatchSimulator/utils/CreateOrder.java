package com.taxi.DispatchSimulator.utils;

import com.taxi.DispatchSimulator.object.Driver;
import com.taxi.DispatchSimulator.object.Order;
import com.taxi.DispatchSimulator.object.User;

public class CreateOrder {
    /**
     * This class will do the order creating jobs
     * @Creator Jiahui Lu
     * @Time 2018.07.19
     * @Other
     * **/
    public static void printOrder(Order o) {
        System.out.print(o.getUserID()+ " ");
        System.out.print(o.getDriverID()+ " ");
        System.out.print(o.getOrderStartT()+ " ");
        System.out.print(o.getOrderEndT()+ " ");
        System.out.print(o.getManhattanDistance()+ " ");
        System.out.print(o.getOrderStartL());
        System.out.print(o.getOrderEndL());
        System.out.println();
    }

    public static Order createOrder(Integer id, User u, Driver d, double distance) {
        Order order = new Order();

        order.setOrderID(id);
        order.setDriverID(d.getDriverID());
        order.setManhattanDistance(distance);
        order.setUserID(u.getUserID());
        order.setOrderStartT(d.getOnRoadT());
        order.setOrderEndT(d.getOffRoadT());
        order.setOrderStartL("[" + d.getDriverLocation().toString() + "] ");
        order.setOrderEndL("[" + u.getUserAimLocation().toString() + "] ");

        return order;
    }

}
