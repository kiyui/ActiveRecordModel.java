package com.Kiyivinski;

import com.Kiyivinski.ActiveRecordModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

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
        ActiveRecordModel employeeModel = new ActiveRecordModel("Employees", employeeColumns);

        if(jobModel.connect("127.0.0.1", "root", "password", "java", 3306)) {
            try {
                ArrayList<HashMap<String, String>> results =  employeeModel.all();
                for (HashMap<String, String> result: results) {
                    System.out.println("Employee: " + result.get("first") + " registered.");
                }
            } catch (SQLException e) {
                System.out.println("Error connecting to SQL server");
            }
        }
        else {
            System.out.println("Error");
        }

    }
}
