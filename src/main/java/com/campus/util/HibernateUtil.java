package com.campus.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = buildSessionFactory("hibernate.cfg.xml");
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
        }
    }

    public static SessionFactory buildSessionFactory(String configFileName) {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(configFileName);

            // Allow override via system properties or environment variables
            String dbUrl = System.getenv("CAMPUS_DB_URL");
            String dbUser = System.getenv("CAMPUS_DB_USER");
            String dbPass = System.getenv("CAMPUS_DB_PASS");

            if (dbUrl != null && !dbUrl.isBlank()) {
                configuration.setProperty("hibernate.connection.url", dbUrl);
            }
            if (dbUser != null && !dbUser.isBlank()) {
                configuration.setProperty("hibernate.connection.username", dbUser);
            }
            if (dbPass != null) {
                configuration.setProperty("hibernate.connection.password", dbPass);
            }

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed for " + configFileName + ": " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            sessionFactory = buildSessionFactory("hibernate.cfg.xml");
        }
        return sessionFactory;
    }

    public static synchronized void setSessionFactory(SessionFactory customFactory) {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
        sessionFactory = customFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
