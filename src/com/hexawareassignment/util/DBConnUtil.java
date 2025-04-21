package com.hexawareassignment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {
    public static Connection getConnection(String propertyFile) {
        Connection connection = null;
        try {
            Properties props = DBPropertyUtil.loadProperties(propertyFile);
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String pass = props.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("DB connection failed: " + e.getMessage());
        }
        return connection;
    }
}
