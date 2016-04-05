package com.Kiyivinski;

import com.Kiyivinski.ActiveRecordModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void printResults(String message, ArrayList<HashMap<String, String>> results) {
        System.out.println(message);
        for (HashMap<String, String> result: results) {
            System.out.println("Employee: " + result.get("first") + " registered.");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        ArrayList<String> jobColumns = new ArrayList<>();
        jobColumns.add("id");
        jobColumns.add("job");
        jobColumns.add("pay");

        ArrayList<String> employeeColumns = new ArrayList<>();
        employeeColumns.add("id");
        employeeColumns.add("first");
        employeeColumns.add("last");

	    ActiveRecordModel jobModel = new ActiveRecordModel("jobs", jobColumns, true);
        ActiveRecordModel employeeModel = new ActiveRecordModel("employees", employeeColumns);

        if (jobModel.connect("127.0.0.1", "root", "password", "java", 3306)) {
            try {
                ArrayList<HashMap<String, String>> results = employeeModel.all();
                printResults("Getting all employees", results);

                results = employeeModel.find("2");
                printResults("Getting employee with id 2", results);

                results = employeeModel.where("first", "John");
                printResults("Getting employee with a first name John", results);

            } catch (SQLException e) {
                System.out.println("Error connecting to SQL server");
            }
        }
        else {
            System.out.println("Error");
        }

    }
}
