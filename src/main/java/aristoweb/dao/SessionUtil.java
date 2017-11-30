package aristoweb.dao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class SessionUtil {
    
    private static SessionUtil instance=null;
    private SessionFactory sessionFactory;
    
    public static SessionUtil getInstance(){
    	
    	if(instance == null)
    		instance = new SessionUtil();
    	
            return instance;
    }
    
    private SessionUtil(){
    	try
    	{
        Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    	} catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSession(){
        Session session =  getInstance().sessionFactory.openSession();
        
        return session;
    }
}