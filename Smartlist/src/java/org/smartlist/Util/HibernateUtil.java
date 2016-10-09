package org.smartlist.Util;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session getSession() {
        return getSessionFactory().getCurrentSession();
    }
    
    public static void beginTransaction() {
        getSessionFactory().getCurrentSession().beginTransaction();
    }

    public static void commitTransaction() {
        getSessionFactory().getCurrentSession().getTransaction().commit();
    } 

    public static void rollBackTransaction() {
        getSessionFactory().getCurrentSession().getTransaction().rollback();
    }

    public static void closeSession() {
        getSessionFactory().getCurrentSession().close();
    }
}
