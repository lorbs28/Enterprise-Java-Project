package Project1;

import java.util.*;
import java.util.logging.*;
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
        ProcessOrder  p  = new ProcessOrder();
        OrderDriver d = new OrderDriver();
        Properties properties = new Properties();
        
        if (true) {
            System.getProperties().setProperty("java.security.policy",
                    "/Users/lorbs28/NetBeansProjects/Project/security_policy.policy");
            System.setSecurityManager(new SecurityManager());
        }
        // Load the properties file
        d.loadProperties();
        
        // Initialize the logger
        d.createLoggerAndLog();
        
        // Run the application
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
    
    public void createLoggerAndLog()
    {
        String logName = System.getProperty("log.name");
        String logFile = System.getProperty("log.filename");
        Logger myLogger = Logger.getLogger(logName);  //creates a logger with this name
        FileHandler oFileHandler;
        
        try
        {
            //establishes the file that will be used for logging; the boolean
            //configures it to append to the file and sets the formatting for the entries
            if(System.getProperty("os.name").indexOf("Windows") >= 0)  //determine type of OS
            {
                //use Windows filesystem if on Windows
                oFileHandler = new FileHandler(logFile, true);
                oFileHandler.setFormatter(new SimpleFormatter());
            }
            else
            {
                //use *nix filesystem if on some flavor of unix
                oFileHandler = new FileHandler(logFile, true);
                oFileHandler.setFormatter(new SimpleFormatter());
            }

            myLogger.addHandler(oFileHandler);  //establishes the handler for the logging facility
            myLogger.setLevel(Level.ALL);  //specifies that all messages be logged

            

        }
        catch(Exception Ex)
        {
            System.out.println("*** Could not initialize the logger for our application ***");
            Ex.printStackTrace();
        }
    }
    
}