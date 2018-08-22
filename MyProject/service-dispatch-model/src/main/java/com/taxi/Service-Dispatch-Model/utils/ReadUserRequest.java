package com.taxi.DispatchSimulator.utils;

import com.taxi.DispatchSimulator.geo.GeoPoint;
import com.taxi.DispatchSimulator.object.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ReadUserRequest {
    /**
     * This class will read user request
     * @parameter lines of userID read from the source
     * @Creator Jiahui Lu
     * @Time 2018.07.19
     * @Other
     * **/
    private List<User> userList;
    private Queue<User> userQueue;

    private int lines;

    public ReadUserRequest(int lines) {
        int index = 0;
        this.userList = new ArrayList<>();
        this.userQueue = new LinkedList<>();
        String csvFile = "order_20161101.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] client = line.split(cvsSplitBy);
                User u = new User();
                u.setUserID(client[0]);
                u.setStartT(Integer.parseInt(client[1]));
                u.setEndT(Integer.parseInt(client[2]));
                GeoPoint getInLocation = new GeoPoint(Double.parseDouble(client[3]),
                                                      Double.parseDouble(client[4]));
                GeoPoint getOffLocation = new GeoPoint(Double.parseDouble(client[5]),
                                                       Double.parseDouble(client[6]));
                u.setUserLocation(getInLocation);
                u.setUserAimLocation(getOffLocation);

                userList.add(u);
                userQueue.add(u);
                index++;
                if (index == lines) { break; }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public Queue<User> getUserQueue() {
        return userQueue;
    }

}
