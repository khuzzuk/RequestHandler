package com.epam.rh;

import java.util.concurrent.Executors;

class SecondarySocket {
    public static void main(String[] args) {
        SecondarySocket secondarySocket = new SecondarySocket();
        secondarySocket.loopConnections();
    }

    private void loopConnections() {
        SocketManager manager = new SocketManager(Executors.newFixedThreadPool(1));
        while (true) {
            manager.submitConnection();
        }
    }
}
