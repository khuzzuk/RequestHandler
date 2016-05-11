package com.epam.rh.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DAOHandler {
    private SessionFactory factory;
    private Session session;
    Session getCurrentSession(){
        if (factory==null) factory= new Configuration().configure().buildSessionFactory();
        return session==null ? factory.openSession() : session;
    }
}
