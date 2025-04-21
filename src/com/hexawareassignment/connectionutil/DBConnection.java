//Task 9
package com.hexawareassignment.connectionutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBConnection {
    private static Connection connection;

    // Load connection properties from a file
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load properties from the file
                InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                Properties prop = new Properties();
                prop.load(input);

                // Get connection details
                String url = prop.getProperty("db.url");
                String user = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");

                // Create a connection to the database
                connection = DriverManager.getConnection(url, user, password);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
