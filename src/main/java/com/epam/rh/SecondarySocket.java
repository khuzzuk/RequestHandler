package com.epam.rh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SecondarySocket {
    public static void main(String[] args) {
        try (
                Socket server = new Socket("localhost",60000);
                PrintWriter writer = new PrintWriter(server.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
                //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
                ){
            String serverMessage, clientMessage;
            while ((serverMessage=reader.readLine())!=null){
                System.out.println("server: "+serverMessage);
                clientMessage = "message";
                if (clientMessage!=null){
                    System.out.println("client: "+clientMessage);
                    writer.println(clientMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
