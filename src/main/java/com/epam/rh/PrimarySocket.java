package com.epam.rh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PrimarySocket {
    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(60000);
                Socket client = serverSocket.accept();
                PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))
        ) {
            System.out.println("connection made");
            String input, output;
            writer.println("first communication");
            while ((input = reader.readLine())!=null){
                System.out.println(input);
                output = Double.toString(Math.random());
                writer.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
