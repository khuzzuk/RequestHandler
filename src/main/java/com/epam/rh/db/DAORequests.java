package com.epam.rh.db;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAORequests {
    private DAOHandler handler;

    public DAORequests(DAOHandler handler) {
        this.handler = handler;
    }

    public synchronized void saveRequest(String message){
        try {
            PreparedStatement statement = handler.getCurrentSession().prepareStatement("INSERT INTO Messages(message) VALUES ('"+message+"');");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
