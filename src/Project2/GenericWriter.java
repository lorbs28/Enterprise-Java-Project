package Project2;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Class:         GenericWriter
 * Function/Duty: This class is a generic writer class.  It has no references to
 *                other classes except those that can be found in the API
 * 
 * @author Bryan Lor
 */
public class GenericWriter implements Runnable {
    
    private Logger myLogger;  //instance variable for logging
    private String ledgerOutput;
    private boolean stopper;
    
    /**
     * ProcessOrder constructor
     */
    public GenericWriter() {
        super();
        myLogger = Logger.getLogger("mysys");
    }

    /**
     * ProcessOrder constructor
     * @param ledgerOutput 
     */
    public GenericWriter(String ledgerOutput) {
        this();
        
        this.ledgerOutput = ledgerOutput;
    }
    
    /**
     * Getter: ledgerOutput
     * @return ledgerOutput
     */
    public String getLedgerOutput() {
        return ledgerOutput;
    }

    
    /**
     * Setter: ledgerOutput
     * @param ledgerOutput
     */
    public void setLedgerOutput(String ledgerOutput) {
        this.ledgerOutput = ledgerOutput;
    }

    public Logger getMyLogger() {
        return myLogger;
    }

    public void setMyLogger(Logger myLogger) {
        this.myLogger = myLogger;
    }

    public boolean isStopper() {
        return stopper;
    }

    public void setStopper(boolean stopper) {
        this.stopper = stopper;
    }
    
    
    
    /**
     * Method: run
     * Purpose: This method will start the writeToFile() method.
     * Input: n/a
     * Output: n/a
     */
    public void run() {
        writeToFile();
        myLogger.log(Level.INFO, "Killing the thread for GenericWriter.");
    }
    
    /**
     * Method: writeToFile
     * Purpose: This method will write a compiled/buffered string to an output file
     * Input: n/a
     * Output: n/a
     */
    public void writeToFile() {
        
        String outputFilename = System.getProperty("output.filename");
        
        PrintWriter pr = null;
        
        try {
            
            /*
             * Begin input checks for the file name
             */
            if(outputFilename == null || outputFilename.length () == 0) {
                myLogger.log(Level.SEVERE, "Filename cannot be empty!");
                throw new Exception("Filename cannot be empty");
            } else if(outputFilename.length() > 512) {
                myLogger.log(Level.SEVERE, "Filename cannot exceed 512 characters!");
                throw new Exception("Filename cannot exceed 512 "
                        + "characters");
            } else if(outputFilename.indexOf(' ') >= 0) {
                myLogger.log(Level.SEVERE, "Filename cannot contain spaces!");
                throw new Exception("Filename cannot contain "
                        + "spaces");
            }
            
            /*
             * While looping, print string to output file, then break out of the loop
             * once the string has been written
             */
            while (!this.stopper) {
            
            myLogger.log(Level.INFO, "Creating print writer.");
            pr  = new PrintWriter(new BufferedWriter(
                    new FileWriter(outputFilename)));
            
            /*
             * Write the string to the output file
             */
            myLogger.log(Level.INFO, "Printing string as output");
            pr.print(this.ledgerOutput);
            myLogger.log(Level.INFO, "Done printing string");
            
            
            // Close the Print Writer
            myLogger.log(Level.INFO, "Closing print writer.");
            pr.close();
            
            break;
            }
            
        File file = new File(outputFilename);
        
        // Begin output checks on the output file
        if(file == null) {
            myLogger.log(Level.SEVERE, "File cannot be empty!");
            throw new Exception("File cannot be empty");
        } else if(file.length() > 1000000000) {
            myLogger.log(Level.SEVERE, "File cannot exceed 1 Gb in size!");
            throw new Exception("File cannot exceed 1 Gb in "
                    + "size");
        }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method: allStop
     * Purpose: This method will stop the thread by changing the stopper boolean to true.
     *          This method acts like the setter for the instance variable stopper.
     * Input: n/a
     * Output: n/a
     * @return
     */
    public synchronized void allStop()
    {
        myLogger.log(Level.WARNING, "Attempting to stop the thread");
        this.stopper = true;
    }
    
    /**
     * Method: toString
     * Purpose: This toString incorporates some of the instance variables
     * Input: ledgerOutput
     * Output: ledgerOutput
     * @return
     */
    public String toString()
    {
        StringBuffer strbHold;
        
        strbHold = new StringBuffer();
        strbHold.append("Thread: Generic Writer");
        strbHold.append(System.getProperty("line.separator"));
        strbHold.append("Ledger Output: " + this.ledgerOutput);
        return strbHold.toString ();
    }
    
}
