package com.epam.rh.db;

import com.epam.rh.requests.Request;

import java.util.concurrent.ExecutorService;

public class Bus {
    private ExecutorService channel;
    DAORequests requests;
    private long counter=0;

    public Bus(ExecutorService channel, DAORequests requests) {
        this.channel = channel;
        this.requests = requests;
    }

    public void reportRequest(String requestText){
        channel.submit(new Task(requestText));
    }
    private class Task implements Runnable {
        private String text;

        public Task(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            Request request = new Request();
            request.setMessage(text);
            requests.saveRequest(request);
        }
    }
}
