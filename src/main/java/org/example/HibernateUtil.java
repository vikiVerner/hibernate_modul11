package org.example;

import client.Client;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import planet.Planet;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;
    private SessionFactory sessionFactory;
    static{
        INSTANCE = new HibernateUtil();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private HibernateUtil(){
         sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }
    public static HibernateUtil getInstance() {

        return INSTANCE;
    }
    public void close() {

        sessionFactory.close();
    }
}
