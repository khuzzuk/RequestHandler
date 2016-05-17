package com.epam.rh.db;

import lombok.NoArgsConstructor;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Repository
@PropertySource("classpath:/postgresJDBC.properties")
@NoArgsConstructor
public class DAOHandler {

    @Autowired
    private Environment env;
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
        source = new Jdbc3PoolingDataSource();
        source.setDataSourceName("PostgreSQL data source");
        source.setServerName(env.getProperty("hostName"));
        source.setDatabaseName(env.getProperty("dataBaseName"));
        source.setUser(env.getProperty("psql.user"));
        source.setPassword(env.getProperty("password"));
        source.setMaxConnections(connectionsNumber);
        connections = new ArrayBlockingQueue<>(connectionsNumber);
        try {
            for (int i = 0; i < connectionsNumber; i++)
                connections.offer(source.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
