package com.test.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection connection = null;
    public static Connection getConnection() throws Exception {
        if (connection != null && connection.isClosed()) {
            connection = null;
        }
        if (connection == null) {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myURL = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
            Class.forName(myDriver);
            connection = DriverManager.getConnection(myURL, "root", "");
        }
        return connection;
    }
}

