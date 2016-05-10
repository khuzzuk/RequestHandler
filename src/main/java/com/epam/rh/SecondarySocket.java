package com.epam.rh;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class SecondarySocket {
    public static void main(String[] args) {
        SecondarySocket secondarySocket = new SecondarySocket();
        secondarySocket.loopConnections();
    }

    private void loopConnections() {
        while (true) {
            try (
                    Socket server = new Socket("localhost", 60000);
                    PrintWriter writer = new PrintWriter(server.getOutputStream(), true)
            ) {
                writer.println("message");
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
