package com.epam.rh.db;

import org.hibernate.LazyInitializationException;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DAOHandler {

    private Connection connection;
    private DataSource dataSource;
    private Jdbc3PoolingDataSource source;

    public DAOHandler() {
    }

    Connection getCurrentSession() {
        if (connection == null)
            throw new LazyInitializationException("Connection should be first initialized with initializeConnection method.");
        return connection;
    }

    public void initializeConnection() {
        String dbAddress = "jdbc:postgresql://localhost:5432/requests";
        Properties properties = loadProperties();
        source = new Jdbc3PoolingDataSource();
        source.setDataSourceName("PostgreSQL data source");
        source.setServerName("localhost");
        source.setDatabaseName("requests");
        source.setUser("r_admin");
        source.setPassword("1q2w");
        source.setMaxConnections(30);
        try {
            connection = source.getConnection();
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
