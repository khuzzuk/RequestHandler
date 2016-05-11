package com.epam.rh.db;

import com.epam.rh.requests.Request;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAORequests {
    private DAOHandler handler;

    public DAORequests(DAOHandler handler) {
        this.handler = handler;
    }

    public void saveRequest(Request request){
        Session session = handler.getCurrentSession();
        Transaction tx = session.beginTransaction();
        tx.begin();
        session.save(request);
        tx.commit();
    }
}
