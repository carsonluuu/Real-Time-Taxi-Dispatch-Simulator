package com.taxi.DispatchSimulator.utils;

import com.taxi.DispatchSimulator.geo.GeoPoint;
import com.taxi.DispatchSimulator.object.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadAcceptTable {

    private Map<String, Map<String, Double>> acceptRateTable;

    public ReadAcceptTable() {
        String csvFile = "AcceptRate.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        ReadDriverID readDriverID = new ReadDriverID();
        List<String> d = readDriverID.getDriverIDList();

        acceptRateTable = new HashMap<>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            int Rindex = 0;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] client = line.split(cvsSplitBy);
                int Cindex = 0;
                String Did = d.get(Rindex);
                acceptRateTable.put(Did, new HashMap<>());
                for (int i = 0; i <= 5; i++) {
                    for (int j = 0; j <= 3; j++) {
                        acceptRateTable.get(Did).put(i + " " + j, Double.parseDouble(client[Cindex++]));
                    }
                }
                Rindex++;
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

    public Map<String, Map<String, Double>> getAcceptRateTable() {
        return acceptRateTable;
    }
}
