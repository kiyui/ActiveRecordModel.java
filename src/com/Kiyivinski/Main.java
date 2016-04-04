package com.Kiyivinski;

import com.Kiyivinski.ActiveRecordModel;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> jobColumns = new ArrayList<String>();
        jobColumns.add("id");
        jobColumns.add("job");
        jobColumns.add("pay");

        ArrayList<String> employeeColumns = new ArrayList<String>();
        employeeColumns.add("id");
        employeeColumns.add("first");
        employeeColumns.add("last");

	    ActiveRecordModel jobModel = new ActiveRecordModel("jobs", jobColumns);
        ActiveRecordModel employeeModel = new ActiveRecordModel("employees", employeeColumns);

        jobModel.connect("127.0.0.1", "root", "password", "java");
    }
}
