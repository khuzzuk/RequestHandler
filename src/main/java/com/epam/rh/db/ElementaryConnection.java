package com.epam.rh.db;

import java.sql.Connection;

class ElementaryConnection {
    private Connection connection;
    private boolean isWorking;

    public ElementaryConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
    public void sendToWork(){
        isWorking=true;
    }
    public boolean isAvailable(){
        return !isWorking;
    }
    public void finished(){
        isWorking=false;
    }
}
