package Project1;

import Project2.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Class:         Process Order
 * Function/Duty: This class will read the orders in, process the values, 
 *                and also write out an output to a new text file
 * 
 * @author Bryan Lor
 */
public class ProcessOrder {


    private List orderInfo = new ArrayList();
    private Map orders = new Hashtable();
    private Logger myLogger;  //instance variable for logging

    /**
     * ProcessOrder constructor
     */
    public ProcessOrder() {
        myLogger = Logger.getLogger("syslog");
    }

    /**
     * Getter: orderInfo
     * @return orderInfo
     */
    public List getOrderInfo() {
        return orderInfo;
    }

    /**
     * Getter: orders
     * @return orders
     */
    public Map getOrders() {
        return orders;
    }

    
    /**
     * Setter: orderInfo
     * @param orderInfo
     */
    public void setOrderInfo(List orderInfo) {
        this.orderInfo = orderInfo;
    }

    /**
     * Setter: orders
     * @param orders
     */
    public void setOrders(Map orders) {
        this.orders = orders;
    }
   
    /**
     * Method: readOrder
     * Purpose: This method will read the input file that has the incoming order
     *          info in it and then add each line of the file as a String to 
     *          the orderInfo array list
     * Input: input filename from properties
     * Output: n/a
     * @throws OrderCustomException
     */
    public void readOrder() throws OrderCustomException {
        String inputFilename = System.getProperty("input.filename");
        
        try {
            
            /* 
             * Begin input checks on the input file name 
             */
            if(inputFilename == null || inputFilename.length () == 0)
                throw new OrderCustomException("Filename cannot be empty");
            else if(inputFilename.length() > 512)
                throw new OrderCustomException("Filename cannot exceed 512 "
                        + "characters");
            else if(inputFilename.indexOf(' ') >= 0)
                throw new OrderCustomException("Filename cannot contain "
                        + "spaces");

            // Declare a new Buffered Reader to read the input file
            BufferedReader  br  = new BufferedReader(new FileReader(
                    inputFilename));
            
            /* 
             * Loop through the input file, reading each line and adding it into
             * the Order Info array list
             */
            
            myLogger.log(Level.INFO, "Reading the order input file.");
            
            while (br.ready()) {
                orderInfo.add(br.readLine());
            }
            
            myLogger.log(Level.INFO, "Done reading the order input file.");
            // Close the Buffered Reader
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        // Begin output checks on the array list
        if(orderInfo == null || orderInfo.size() < 1) {
            throw new OrderCustomException("OrderInfo array list cannot be null or empty");
        }
        
    }
     
    /**
     * Method: readCharges
     * Purpose: This method will read the charges.dat file, then split the Strings
     *          by empty space, putting the first value into the item range array
     *          and the second value into the item charge array.  After looping
     *          through the charges.dat file and placing the values into the arrays,
     *          the arrays will then be set with the appropriate call to the Order
     *          object that was passed in as a parameter
     * Input: charges filename from properties
     * Output: item charge/range arrays
     * @param d
     * @throws OrderCustomException
     */
    public void readCharges(Order d) throws OrderCustomException {
        int[]     itemRange   = new int[4];
        double[]  itemCharge  = new double[4];
        String[]  sarray      = new String[2];
        int       counter     = 0;
        
        String chargesFilename = System.getProperty("charges.filename");
        
        try {
            // Begin input checks on the output file name
            if(chargesFilename == null || chargesFilename.length () == 0) {
                throw new OrderCustomException("Filename cannot be empty");
            }
            else if(chargesFilename.length() > 512) {
                throw new OrderCustomException("Filename cannot exceed 512 "
                        + "characters");
            }
            else if(chargesFilename.indexOf(' ') >= 0) {
                throw new OrderCustomException("Filename cannot contain "
                        + "spaces");
            }
            
            BufferedReader  br  = new BufferedReader(new FileReader(
                    chargesFilename));
            
            // While Buffered Reader is ready, read each line, split each line by
            // empty spaces, parsing the first value as an integer and second value as
            // a double.  Then increment counter by 1.
            myLogger.log(Level.INFO, "Reading the charges file.");
            
            while (br.ready()) {
                String  s  = br.readLine();
                sarray = s.split(" ");
                itemRange[counter] = Integer.parseInt(sarray[0]);
                itemCharge[counter] = Double.parseDouble(sarray[1]);
                counter++;
            }
            
            myLogger.log(Level.INFO, "Done reading the charges file.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        // Begin output checks on the arrays being passed
        if(sarray == null || sarray.length < 1) {
            throw new OrderCustomException("Array cannot be null or empty");
        } else if(itemRange.length < 1 || itemRange == null) {
            throw new OrderCustomException("Itemrange array has no elements or is null");
        } else if (itemCharge.length < 1 || itemRange == null) {
            throw new OrderCustomException("Itemrange array has no elements or is null");
        }
        
        d.setHChargeQuantity(itemRange);
        d.setHChargeAmount(itemCharge);
    }

    /**
     * Method: createOrder
     * Purpose: This method will iterate through the order info array list for 
     *          all elements, which are Strings, and then split those Strings by
     *          empty spaces and set the values accordingly into the Order object.
     *          Then call readCharges() to figure out the item charge.
     *          Put the Order object into the orders map as a value with the key
     *          being the customer name
     * Input: order info array list
     * Output: order map with order object as value
     * @throws OrderCustomException
     */
    public void createOrder() throws OrderCustomException{
        // Iterate through the Order Info array list, splitting tokens inside 
        // each string by empty spaces, then set the values into the new Order
        // object
        
        myLogger.log(Level.INFO, "Creating the orders");
        for (int i = 0; i < orderInfo.size(); i++) {
            Order     d       = new Order();
            String[]  sorder  = ((String) orderInfo.get(i)).split("\\s");
            d.setCustomerName(sorder[0]);
            d.setCustomerNumber(Integer.parseInt(sorder[1]));
            d.setQuantity(Integer.parseInt(sorder[2]));
            d.setUnitPrice(Double.parseDouble(sorder[3]));
            d.setItem(sorder[4]);
            readCharges(d);
            orders.put((String) sorder[0], d);
        }
        myLogger.log(Level.INFO, "Finished creating the orders");
        
    }

    /**
     * Method: writeOutput
     * Purpose: This method will iterate through the map, calling the display method
     *          of the Order object and writing it to the output text file.
     * Input: orders map
     * Output: output file with formatted order info
     * @throws OrderCustomException
     */
    public void writeOutput() throws OrderCustomException {
        Iterator  i  = orders.entrySet().iterator();
        PrintWriter  pr = null;
        
        String outputFilename = System.getProperty("output.filename");
        
        try {
            
            // Begin input checks on the output file name
            if(outputFilename == null || outputFilename.length () == 0) {
                throw new OrderCustomException("Filename cannot be empty");
            }
            else if(outputFilename.length() > 512) {
                throw new OrderCustomException("Filename cannot exceed 512 "
                        + "characters");
            }
            else if(outputFilename.indexOf(' ') >= 0) {
                throw new OrderCustomException("Filename cannot contain "
                        + "spaces");
            }

            pr  = new PrintWriter(new BufferedWriter(
                    new FileWriter(outputFilename)));
            
            // Iterate through the Map and print out the display
            // to the output file
            
            myLogger.log(Level.INFO, "Writing output to output file.");
            
            while (i.hasNext()) {
                Map.Entry  me  = (Map.Entry) i.next();
                Order      d   = (Order) me.getValue();
                pr.println(d.display());
            }
            myLogger.log(Level.INFO, "Done writing output to output file.");
            
            
            // Close the Print Writer
            pr.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        File file = new File(outputFilename);
        
        // Begin output checks on the output file
        if(file == null) {
            throw new OrderCustomException("File cannot be empty");
        } else if(file.length() > 1000000000) {
            throw new OrderCustomException("File cannot exceed 1 Gb in "
                    + "size");
        }

    }
    
    public String compileStringsForOutput() {
        Iterator  i  = orders.entrySet().iterator();
        String ledgerOutput = "";
            
            myLogger.log(Level.INFO, "Compiling output to send to GenericWriter.");

            while (i.hasNext()) {
                Map.Entry  me  = (Map.Entry) i.next();
                Order      d   = (Order) me.getValue();
                ledgerOutput += d.display();
            }
            
            myLogger.log(Level.INFO, "Done compiling output string to send to GenericWriter.");

        return ledgerOutput;
    }

    /**
     * Method: run
     * Purpose: This method will run the readOrder(), createOrder(), and
     *          writeOutput() methods.
     * Input: n/a
     * Output: n/a
     * @throws OrderCustomException
     */
    public void run() throws OrderCustomException{
        
        boolean threadDeterminer = Boolean.parseBoolean(System.getProperty("multi.thread"));
        
        /*
         * If the threadDeterminer is true, then it should run as a multi-thread (concurrent)
         */
        if (threadDeterminer) {
            readOrder();
            createOrder();
            
            String stringToGenericWriter = compileStringsForOutput();
            
            myLogger.log(Level.INFO, "Creating and running the object instance for GenericWriter class");
            GenericWriter writer = new GenericWriter(stringToGenericWriter);
            
            writer.start();
            
            myLogger.log(Level.INFO, "Creating and running the object instance for GenericScanner class");
            GenericScanner scanner = new GenericScanner(this.orders);
            scanner.start();
            
        } else {
            
            /*
             * If the threadDeterminer is false, then it should run sequentially
             */
            myLogger.log(Level.INFO, "Thread Determiner = FALSE, running application sequentially.");

            readOrder();
            createOrder();
            writeOutput();
        
        }
    }
    
    /**
     * Method: toString
     * Purpose: This toString incorporates the instance variables
     * Input: order info array list, orders map
     * Output: order info array list, orders map
     * @return
     */
    
    public String toString() {
        return "\nToString for ProcessOrder: \n" + getOrderInfo() + "\n" + getOrders();
    }

}