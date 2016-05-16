package com.epam.rh.db;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@NoArgsConstructor
public class Bus {
    @Autowired
    private ExecutorService channel;
    @Autowired
    DAORequests requests;
    private AtomicInteger counter = new AtomicInteger(0);
    private long startTime;

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
            requests.saveRequest(text);
            int a = counter.addAndGet(1);
            if (a>=10000){
                counter = new AtomicInteger(0);
                System.out.println("writing to DB rate: "+(a*1000/(System.currentTimeMillis()-startTime)));
                startTime = System.currentTimeMillis();
            }
        }
    }
}
