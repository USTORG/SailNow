package com.sailnow.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.sailnow.core.ConfigurationConstants;

public class HibernateUtil {

	//to disallow creating objects by other classes.
    public static SessionFactory factory;
    
    private HibernateUtil(){
    	
    }
    
    public static synchronized SessionFactory getSessionFactory() {
    	 
        if (factory == null) {
        	Configuration configuration = new Configuration().configure(ConfigurationConstants.HIBERNATE_CFG_FILE);
        	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        	factory = configuration.buildSessionFactory(builder.build());
        }
        return factory;
    }

}
