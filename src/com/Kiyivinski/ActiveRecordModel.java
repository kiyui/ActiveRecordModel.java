package com.Kiyivinski;

import java.sql.*;
import org.mariadb.jdbc.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ActiveRecordModel {
    private static String hostname;
    private static String username;
    private static String password;
    private static String database;
    private static Integer port;
    private static Connection connection;
    private String table;
    private ArrayList<String> columns;
    private Boolean verbose;

    public ActiveRecordModel(String table, ArrayList<String> columns, Boolean verbose) {
        this.table = table;
        this.columns = columns;
        this.verbose = verbose;
    }

    public ActiveRecordModel(String table, ArrayList<String> columns) {
        this(table, columns, false);
    }

    public ArrayList<String> getColumns() {
        return this.columns;
    }

    public Boolean connect(String hostname, String username, String password, String database, Integer port) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;
        this.connection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String address = "jdbc:mariadb://" + this.hostname
                    + ":" + this.port
                    + "/" + this.database
                    + "?user=" + this.username
                    + "&password=" + this.password;
            if (this.verbose)
                System.out.println("Using connection address: " + address);
            this.connection = DriverManager.getConnection(address);
            return true;
        } catch(SQLException e) {
            if (this.verbose)
                System.out.println("SQL Connection error.");
            return false;
        } catch (Exception e) {
            if (this.verbose)
                System.out.println("Unknown error at `connect()`.");
            return false;
        }
    }

    public Boolean connect(String hostname, String username, String password, String database) {
        return connect(hostname, username, password, database, 3306);
    }

    public ArrayList<HashMap<String, String>> all() throws SQLException {
        try {
            Statement statement =  this.connection.createStatement();

            String sql = "SELECT * from " + this.table;
            ResultSet resultSet = statement.executeQuery(sql);

            ArrayList<HashMap<String, String>> results = new ArrayList<>();
            while(resultSet.next()) {
                HashMap<String, String> result = new HashMap<>();
                for (String column: this.columns) {
                    result.put(column, resultSet.getString(column));
                    if (this.verbose)
                        System.out.print(resultSet.getString(column) + ' ');
                }
                if (this.verbose)
                    System.out.println("");
                results.add(result);
            }
            return results;
        } catch (SQLException e) {
            if (this.verbose)
                System.out.println("Caught SQL error at `all()`.");
            throw e;
        }
    }
}

