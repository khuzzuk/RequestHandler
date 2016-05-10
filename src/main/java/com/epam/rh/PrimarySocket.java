package com.epam.rh;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

class PrimarySocket {

    private long counter;
    private long startTime;
    private SocketManager manager;

    private PrimarySocket() {
        counter = 0;
    }

    public static void main(String[] args) {
        PrimarySocket socketServer = new PrimarySocket();
        socketServer.startListening();
    }

    private void startListening(){
        initializeManager();
        try {
            ServerSocket serverSocket = new ServerSocket(60000);
            listen(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen(ServerSocket serverSocket) throws IOException {
        acceptRequest(serverSocket);
        startTime=System.currentTimeMillis();
        while (true){
            acceptRequest(serverSocket);
            if (counter==300000) break;
        }
        close();
    }

    private void acceptRequest(ServerSocket serverSocket) throws IOException {
        handleMessage(serverSocket.accept());
        if (counter % 10000 == 0) {
            System.out.println(counter);
        }
    }

    private void initializeManager(){
        manager = new SocketManager(Executors.newFixedThreadPool(5));
    }

    private void close() {
        long endTime = System.currentTimeMillis();
        System.out.println("Connections per second = "+(counter*1000/(endTime -startTime)));
    }

    private void handleMessage(Socket client) {
        counter++;
        manager.closeSocket(client);
    }
}
