/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Project2;

import java.io.*;
import java.util.logging.*;

/**
 *
 * @author lorbs28
 */
public class GenericWriter extends Thread {
    
    private Logger myLogger;  //instance variable for logging
    private String ledgerOutput;
    private boolean stopper;
    
    public GenericWriter() {
        myLogger = Logger.getLogger("syslog");
    }

    public GenericWriter(String ledgerOutput) {
        this();
        
        this.ledgerOutput = ledgerOutput;
    }
    
    public void run() {
        writeToFile();
        myLogger.log(Level.INFO, "Killing the thread for GenericWriter.");
    }
    
    public void writeToFile() {
        
        String outputFilename = System.getProperty("output.filename");
        
        PrintWriter pr = null;
        
        try {
            
            while (!this.stopper) {
                
            pr  = new PrintWriter(new BufferedWriter(
                    new FileWriter(outputFilename)));
            
            /*
             * Write the string to the output file
             */
            myLogger.log(Level.INFO, "Printing string as output");
            pr.print(this.ledgerOutput);
            myLogger.log(Level.INFO, "Done printing string");
            
            
            // Close the Print Writer
            pr.close();
            
            break;
            }
            
        
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getLedgerOutput()
    {
        return this.ledgerOutput;
    }
    
    public void allStop()
    {
        myLogger.log(Level.WARNING, "Attempting to stop the thread");
        this.stopper = true;
    }
        
    public String toString()
    {
        StringBuffer strbHold;
        
        strbHold = new StringBuffer();
        strbHold.append("Thread: Generic Writer ");
        strbHold.append(System.getProperty("line.separator"));
        strbHold.append("Ledger Output: " + this.ledgerOutput);
        return strbHold.toString ();
    }
    
}
