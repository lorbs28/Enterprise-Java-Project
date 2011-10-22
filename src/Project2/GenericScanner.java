/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Project2;

import java.util.*;
import java.util.logging.*;

/**
 *
 * @author lorbs28
 */
public class GenericScanner extends Thread {
    
    private Logger myLogger;  //instance variable for logging
    private boolean stopper;
    private Map orders = new Hashtable();
    private int currentOrderSize, orderSize;

    public GenericScanner() {
        myLogger = Logger.getLogger("syslog");
    }

    public GenericScanner(Map orders) {
        this();   
        
        this.orders = orders;
        
        orderSize = orders.size();
    }

    public int getCurrentOrderSize() {
        return currentOrderSize;
    }

    public void setCurrentOrderSize(int currentOrderSize) {
        this.currentOrderSize = currentOrderSize;
    }

    public int getOrderSize() {
        return orderSize;
    }

    public void setOrderSize(int orderSize) {
        this.orderSize = orderSize;
    }

    public Map getOrders() {
        return orders;
    }

    public void setOrders(Map orders) {
        this.orders = orders;
    }
    
    
    public void run() {
        checkLedger();
        myLogger.log(Level.INFO, "Killing the thread for GenericScanner.");
    }
    
    public void checkLedger() {
        try {
            
            
            
            while(!this.stopper) {
                this.currentOrderSize = orders.size();
                
                if (this.currentOrderSize > this.orderSize) {
                    myLogger.log(Level.INFO, "An entry was added to the ledger at: " + new Date().toString());
                    this.orderSize = this.currentOrderSize;
                }
               
                Thread.sleep(2000);
            }
         } catch(Exception e) {
            myLogger.log(Level.SEVERE, "Encountered an exception: " + e.getMessage());
            System.out.println("Encountered an exception at " + new java.util.Date());
            e.printStackTrace();
        }
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
        strbHold.append("Thread: GenericScanner");
        strbHold.append(System.getProperty("line.separator"));
        strbHold.append("Orders map has currently: " + this.orders);
        return strbHold.toString ();
    }
}
