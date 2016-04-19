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

    private ArrayList<HashMap<String, String>> get(String sql) throws SQLException {
        try {
            Statement statement =  this.connection.createStatement();

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
            if (this.verbose) {
                System.out.println("Caught SQL error at `get()`.");
                System.out.println(e.toString());
            }
            throw e;
        }
    }

    public ArrayList<HashMap<String, String>> query(String sql) throws SQLException {
        try {
            ArrayList<HashMap<String, String>> results = this.get(sql);
            return results;
        } catch (SQLException e) {
            if (this.verbose)
                System.out.println("Caught SQL error at `query()`.");
            throw e;
        }
    }

    public ArrayList<HashMap<String, String>> all() throws SQLException {
        try {
            String sql = "SELECT * from " + this.table;

            ArrayList<HashMap<String, String>> results = this.get(sql);
            return results;
        } catch (SQLException e) {
            if (this.verbose)
                System.out.println("Caught SQL error at `all()`.");
            throw e;
        }
    }

    public ArrayList<HashMap<String, String>> find(String id) throws SQLException {
        try {
            String sql = "SELECT * from " + this.table + " where " + this.table + ".id = " + id;

            ArrayList<HashMap<String, String>> results = this.get(sql);
            return results;
        } catch (SQLException e) {
            if (this.verbose)
                System.out.println("Caught SQL error at `find()`.");
            throw e;
        }
    }

    public ArrayList<HashMap<String, String>> where(String key, String value) throws SQLException {
        try {
            String sql = "SELECT * from " + this.table + " where " + this.table + "." + key + " = \"" + value + "\"";

            ArrayList<HashMap<String, String>> results = this.get(sql);
            return results;
        } catch (SQLException e) {
            if (this.verbose)
                System.out.println("Caught SQL error at `where()`.");
            throw e;
        }
    }

    public ArrayList<HashMap<String, String>> create(HashMap<String, String> pairs) throws SQLException {
        try {
            String sql = "INSERT INTO " + this.table + " (";
            String values = "(";
            for (String key: pairs.keySet()) {
                sql += "`" + key + "`, ";
                values += "'" + pairs.get(key) + "', ";
            }
            values = values.substring(0, values.length() - 2) + ");";
            sql = sql.substring(0, sql.length() - 2) + ") VALUES " + values;
            System.out.println(sql);
            ArrayList<HashMap<String, String>> results = this.get(sql);
            return results;

        } catch (SQLException e) {
            if (this.verbose)
                System.out.println("Caught SQL error at `create()`.");
            throw e;
        }
    }

    public ArrayList<HashMap<String, String>> update(Integer id, HashMap<String, String> pairs) throws SQLException {
        try {
            String sql = "UPDATE " + this.table + " SET ";
            String values = "";
            for (String key: pairs.keySet()) {
                values += key + "='" + pairs.get(key) + "', ";
            }
            values = values.substring(0, values.length() - 2);
            sql += values + " WHERE ID='" + id.toString() + "'";
            System.out.println(sql);
            ArrayList<HashMap<String, String>> results = this.get(sql);
            return results;

        } catch (SQLException e) {
            if (this.verbose)
                System.out.println("Caught SQL error at `create()`.");
            throw e;
        }
    }
}

