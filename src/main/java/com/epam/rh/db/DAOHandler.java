package com.epam.rh.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOHandler {

    private Connection connection;

    Connection getCurrentSession(){
        if (connection==null) initializeConnection();
        return connection;
    }

    private void initializeConnection() {
        String dbAddress = "jdbc:postgresql://localhost:5432/requests";
        Properties properties = loadProperties();
        try {
            connection = DriverManager.getConnection(dbAddress,properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(DAOHandler.class.getResourceAsStream("/postgresJDBC.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
