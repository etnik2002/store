package com.test.database;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new Driver());
            System.out.println("connecting");
            connection = DriverManager.getConnection("jdbc:mysql://:3306/" + "store_hotelkey", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

