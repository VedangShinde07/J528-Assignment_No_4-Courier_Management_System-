package com.hexaware.tms.util;

/*
 * @Author: Vedang Shinde
 * @Description:  Database Connection.
 * Date: 21 October 2024
 */

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private static Connection connection;


    private static Properties properties = new Properties();


    static {
        try (FileReader reader = new FileReader("resources/DBConfig.properties")) {
  
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load database configuration properties.");
        }
    }

    
    public static String getPropertyString(String key) {
        return properties.getProperty(key);
    }


    public static Connection getConnection() {
        if (connection == null) {
            try {
                String driver = getPropertyString("driver.classname");
                String url = getPropertyString("url");
                String username = getPropertyString("username");
                String password = getPropertyString("password");

                Class.forName(driver);

                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to establish database connection.");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; 
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error occurred while closing the connection.");
            }
        }
    }
}
