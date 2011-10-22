package Project1;


import Project2.*;
import java.util.*;
import java.io.*;

/**
 * Class:         OrderDrive
 * Function/Duty: This class the Order driver class that will call the run() method
 *                from the ProcessOrder class.  This is also the class where
 *                the SecurityManager will be set along with the security policy.
 *                The properties/ini file will also be loaded into System properties
 *                from within this class also.
 * @author Bryan Lor
 */
public class OrderDriver {
    
    /**
     * OrderDrive constructor
     */
    public OrderDriver() {
        
    }

    /**
     * Method: main
     * Purpose: This is the main method where the SecurityManager is set up and 
     *          security policy is set.  Properties will be called and then the 
     *          run method from ProcessOrder will be called.
     * Input: n/a
     * Output: n/a
     * @param args
     * @throws OrderCustomException
     */
    public static void main(String[] args) throws OrderCustomException{
        new SysLogger().init();
        
        ProcessOrder  p  = new ProcessOrder();
        OrderDriver d = new OrderDriver();
        
        Properties properties = new Properties();
        
        // Load the SecurityManager with the security policy
        if(true)
        {
            System.getProperties().setProperty("java.security.policy",
                    "/Users/lorbs28/NetBeansProjects/Project/security_policy.policy");
            System.setSecurityManager(new SecurityManager());     
        }
        
        
        d.loadProperties();
        
        p.run();
    }
    
    /**
     * Method: loadProperties
     * Purpose: This method will load the ini file properties into the System
     *          properties
     * Input: ini properties
     * Output: ini properties
     */
    public void loadProperties() {

        
        try {
            // Load the ini file into System properties
            
            System.getProperties().load(new FileInputStream("properties.ini"));
            
            // line separator?
            
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}