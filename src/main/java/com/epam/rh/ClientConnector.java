package com.epam.rh;

import java.util.concurrent.Executors;

class ClientConnector {
    public static void main(String[] args) {
        ClientConnector clientConnector = new ClientConnector();
        clientConnector.loopConnections();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void loopConnections() {
        SocketManager manager = new SocketManager(Executors.newFixedThreadPool(2));
        while (true) {
            manager.submitSocketConnection();
        }
    }
}
