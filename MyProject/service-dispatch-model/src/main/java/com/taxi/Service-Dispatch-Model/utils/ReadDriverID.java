package com.taxi.DispatchSimulator.utils;

import com.taxi.DispatchSimulator.object.Driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadDriverID {
    /**
     * This class will read driveID
     * @Creator Jiahui Lu
     * @Time 2018.07.18
     * @Other
     * **/
    private List<String> driverIDList;

    public ReadDriverID() {

        driverIDList = new ArrayList<>();
        String csvFile = "Did_full.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] client = line.split(cvsSplitBy);
                driverIDList.add(client[0]);
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

    /** Give the ID list of drivers as a 32 length hashCode **/
    public List<String> getDriverIDList() { return driverIDList;}
}
