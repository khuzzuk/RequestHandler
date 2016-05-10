package com.epam.rh;

import java.io.IOException;
import java.net.Socket;

class SocketCloser implements Runnable {
    private final Socket socket;

    SocketCloser(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
