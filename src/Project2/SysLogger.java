package Project2;

/*
 * SysLogger.java
 *
 * This class is used to provide logging functionality for our system. We will invoke this class
 * at the initialization of our application.
 */
import java.util.logging.*;

public class SysLogger {

    /** Creates a new instance of SysLogger */
    public SysLogger() {
    }

    /**
     * Provide initialization for our class
     */
    public void init()
    {
        Logger myLogger = Logger.getLogger("syslog");  //creates a logger with this name
        FileHandler oFileHandler;

        try
        {
            //establishes the file that will be used for logging; the boolean
            //configures it to append to the file and sets the formatting for the entries
            if(System.getProperty("os.name").indexOf("Windows") >= 0)  //determine type of OS
            {
                //use Windows filesystem if on Windows
                oFileHandler = new FileHandler("mysys.log", true);
                oFileHandler.setFormatter(new SimpleFormatter());
            }
            else
            {
                //use *nix filesystem if on some flavor of unix
                oFileHandler = new FileHandler("mysys.log", true);
                oFileHandler.setFormatter(new SimpleFormatter());
            }

            myLogger.addHandler(oFileHandler);  //establishes the handler for the logging facility
            myLogger.setLevel(Level.ALL);  //specifies that all messages be logged

            //the following call demonstrates the use of the logging facility; the level specified
            //in the first parameter determines the priority that the entry should be printed
            //in the log; when logging, it is good practice to log:
            // 1) environmental changing events (a configuration change, thrown exception, etc.)
            // 2) startup and shutdown of the application
            // 3) significant CRUD (create, read, update, delete) events
            //myLogger.log(Level.INFO, "This is just a test");
            //myLogger.log(Level.WARNING, "This is a warning message");
            //myLogger.log(Level.SEVERE, "This is a message for a severe failure");

        }
        catch(Exception Ex)
        {
            System.out.println("*** Could not initialize the logger for our application ***");
        }
    }

}