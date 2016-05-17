package com.epam.rh;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rh.configuration");
    }
}
