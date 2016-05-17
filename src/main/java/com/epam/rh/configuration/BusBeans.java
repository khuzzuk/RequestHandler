package com.epam.rh.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan(basePackages = {"com.epam.rh"})
public class BusBeans {
    @Bean
    public ExecutorService channel(){
        return Executors.newFixedThreadPool(5);
    }
}
