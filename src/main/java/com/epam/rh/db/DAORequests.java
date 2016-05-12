package com.epam.rh.db;

import com.epam.rh.requests.Request;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAORequests {
    private DAOHandler handler;

    public DAORequests(DAOHandler handler) {
        this.handler = handler;
    }

    public void saveRequest(Request request){
        try {
            PreparedStatement statement = handler.getCurrentSession().prepareStatement("");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
