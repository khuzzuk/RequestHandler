package com.epam.rh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {
    @Autowired
    private ServerInitializer initializer;
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rh.configuration");
        //context.getBean(AppInitializer.class);
    }
}
