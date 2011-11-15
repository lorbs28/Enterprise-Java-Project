package Project2;

import java.util.*;
import java.util.logging.*;

/**
 * Class:         Generic Scanner
 * Function/Duty: This class is a generic scanner class.  It has no references to
 *                other classes except those that can be found in the API
 * 
 * @author Bryan Lor
 */
public class GenericScanner implements Runnable {
    
    private Logger myLogger;  //instance variable for logging
    private boolean stopper;
    private Map orders = new Hashtable();
    private int currentOrderSize, orderSize;

    /**
     * ProcessOrder constructor
     */
    public GenericScanner() {
        super();
        myLogger = Logger.getLogger("mysys");
    }

    /**
     * ProcessOrder constructor
     * @param orders
     */
    public GenericScanner(Map orders) {
        this();   
        
        this.setOrders(orders);
        
        this.setOrderSize(orders.size());
    }

    /**
     * Getter: currentOrderSize
     * @return currentOrderSize
     */
    public int getCurrentOrderSize() {
        return currentOrderSize;
    }

    /**
     * Setter: currentOrderSize
     * @param currentOrderSize
     */
    public synchronized void setCurrentOrderSize(int currentOrderSize) {
        this.currentOrderSize = currentOrderSize;
    }

    /**
     * Getter: orderSize
     * @return orderSize
     */
    public int getOrderSize() {
        return orderSize;
    }

    /**
     * Setter: orderSize
     * @param orderSize
     */
    public synchronized void setOrderSize(int orderSize) {
        this.orderSize = orderSize;
    }

    /**
     * Getter: orders
     * @return orders
     */
    public Map getOrders() {
        return orders;
    }

    /**
     * Setter: orders
     * @param orders
     */
    public void setOrders(Map orders) {
        this.orders = orders;
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
     * Purpose: This method will start the checkLedger() method.
     * Input: n/a
     * Output: n/a
     */
    public void run() {
        checkLedger();
        myLogger.log(Level.INFO, "Killing the thread for GenericScanner.");
    }
    
    /**
     * Method: checkLedger
     * Purpose: This method will continously check the map for any changes in size the log if there are any changes
     * Input: n/a
     * Output: n/a
     */
    public void checkLedger() {
        try {
            
            /*
             * While looping, get the current size of the orders map then determine if
             * the current size is greater or lesser than the previous size.  If there are
             * changes to the size, then log the appropriate message to the log file.
             * The loop will pause ("sleep") for 2 seconds before looping again.
             */
            
            while(!this.stopper) {
                this.currentOrderSize = orders.size();
                
                /*
                 * Determine if the current size is greater or lesser than the previous recorded size.
                 */
                if (this.currentOrderSize > this.orderSize) {
                    myLogger.log(Level.INFO, "An entry was added to the ledger at: " + new Date().toString());
                    this.orderSize = this.currentOrderSize;
                    allStop();
                } else if (this.currentOrderSize < this.orderSize) {
                    myLogger.log(Level.INFO, "An entry was deleted from the ledger at: " + new Date().toString());
                    this.orderSize = this.currentOrderSize;
                    allStop();
                }
               
                Thread.sleep(2000);
            }
         } catch(Exception e) {
            myLogger.log(Level.SEVERE, "Encountered an exception: " + e.getMessage());
            System.out.println("Encountered an exception at " + new java.util.Date());
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
     * Input: orders
     * Output: orders
     * @return
     */
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
