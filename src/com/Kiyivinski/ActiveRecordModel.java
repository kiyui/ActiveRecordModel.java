package com.Kiyivinski;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

public class ActiveRecordModel {
    private static String hostname;
    private static String username;
    private static String password;
    private static String database;
    private static Integer port;
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

    public Boolean connect(String hostname, String username, String password, String database, Integer port) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;
        return true;
    }

    public Boolean connect(String hostname, String username, String password, String database) {
        return connect(hostname, username, password, database, 3306);
    }
}

