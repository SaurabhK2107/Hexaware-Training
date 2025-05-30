package com.hexawareassignment.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static Properties loadProperties(String filename) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filename)) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("Error reading DB properties: " + e.getMessage());
        }
        return props;
    }
}
