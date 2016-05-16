package com.epam.rh.db;

import lombok.NoArgsConstructor;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Repository
@NoArgsConstructor
public class DAOHandler {

    private static final int connectionsNumber = 30;
    private Queue<Connection> connections;
    private Jdbc3PoolingDataSource source;

    Connection getCurrentSession() {
        return connections.poll();
    }
    void ReturnSession(Connection connection){
        connections.offer(connection);
    }

    @PostConstruct
    private void initializeConnection() {
        Properties properties = loadProperties();
        source = new Jdbc3PoolingDataSource();
        source.setDataSourceName("PostgreSQL data source");
        source.setServerName(properties.getProperty("hostName"));
        source.setDatabaseName(properties.getProperty("dataBaseName"));
        source.setUser(properties.getProperty("user"));
        source.setPassword(properties.getProperty("password"));
        source.setMaxConnections(connectionsNumber);
        connections = new ArrayBlockingQueue<>(connectionsNumber);
        try {
            for (int i = 0; i < connectionsNumber; i++)
                connections.offer(source.getConnection());
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
