package com.taxi.DispatchSimulator.utils;

import com.taxi.DispatchSimulator.object.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteToCSV {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "";

    public static void writeCsvFile(List<Order> orders) {

        String fileName = "res.csv";

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            for (Order order : orders) {
                //Write a new student object list to the CSV file
                fileWriter.append(order.getOrderID() + "");
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(order.getDriverID() + "");
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(order.getUserID() + "");
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(order.getManhattanDistance() + "");
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }

    }
}


