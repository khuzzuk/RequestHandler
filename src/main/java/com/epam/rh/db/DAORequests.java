package com.epam.rh.db;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@NoArgsConstructor
public class DAORequests {
    @Autowired
    private DAOHandler handler;

    public synchronized void saveRequest(String message){
        try {
            Connection connection = handler.getCurrentSession();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Messages(message) VALUES ('"+message+"');");
            statement.executeUpdate();
            statement.close();
            handler.ReturnSession(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
