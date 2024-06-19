package com.codtech.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    // JDBC URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/codtech_crm";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "chandu@93467";

    // Method to establish a database connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle connection failure appropriately
        }
        return connection;
    }
}
