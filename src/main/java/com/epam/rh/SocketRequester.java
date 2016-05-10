package com.epam.rh;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class SocketRequester implements Runnable {
    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 60000)){
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("message");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
