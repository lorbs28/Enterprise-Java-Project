/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Project2;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import junit.framework.TestCase;

/**
 *
 * @author lorbs28
 */
public class GenericScannerTest extends TestCase {
    
    public GenericScannerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
//
//    /**
//     * Test of run method, of class GenericScanner.
//     */
//    public void testRun() {
//        System.out.println("testing run for generic scanner");
//        GenericScanner instance = new GenericScanner();
//        instance.run();
//    }
//
    public void testGenericScannerTest()throws FileNotFoundException, IOException {
        testCheckLedger();
        testAllStop();
        testToString();
    }
    
    
    /**
     * Test of checkLedger method, of class GenericScanner.
     */
    public void testCheckLedger() throws FileNotFoundException, IOException {
        System.out.println("testing checkLedger");
        
        GenericScanner instance = new GenericScanner();
        Map expectedMap = new Hashtable();
       
        Logger testLogger = Logger.getLogger("testsyslog");
        FileHandler oFileHandler;

        
        /*
         * Set up the logger for logging in the test data to a log
         */
        try
        {
            /*
             * Delete the current log file if it exists prior to creating a new log
             */
            File file = new File("test/Project2/testsys.log");
            
            file.delete();
            
            if(System.getProperty("os.name").indexOf("Windows") >= 0)  //determine type of OS
            {
                //use Windows filesystem if on Windows
                oFileHandler = new FileHandler("test/Project2/testsys.log", true);
                oFileHandler.setFormatter(new SimpleFormatter());
            }
            else
            {
                //use *nix filesystem if on some flavor of unix
                oFileHandler = new FileHandler("test/Project2/testsys.log", true);
                oFileHandler.setFormatter(new SimpleFormatter());
            }

            testLogger.addHandler(oFileHandler);  
            testLogger.setLevel(Level.ALL);  

        }
        catch(Exception Ex)
        {
            System.out.println("*** Could not initialize the logger for our testing ***");
        }
        
        
        int currentSize = 0;
        int newCurrentSize = 0;
        
        /*
         * Populate the map with test data
         */
        expectedMap.put(1, "testOne");
        expectedMap.put(2, "testTwo");
        expectedMap.put(3, "testThree");
        
        /*
         * Get the current size of the map
         */
        currentSize = expectedMap.size();
        
        /*
         * Add another entry into the map
         */
        expectedMap.put(4, "testFour");
        
        /*
         * Get the new current size of the map after the new entry
         */
        newCurrentSize = expectedMap.size();
        
        String expectedLogString = "A new entry was added to the test log.";
        
        /*
         * Test to see if the new current size is greater than the previous current
         * size.  If it's true, then write an entry into the log.
         */
        if (newCurrentSize > currentSize) {
            testLogger.log(Level.INFO, expectedLogString);
        } else {
            fail("Entry failed to be inserted into log for entry changes.");
        }
        
        /*
         * Prepare a reader to read the log entries back
         */
        BufferedReader  br  = new BufferedReader(new FileReader(
                    "test/Project2/testsys.log"));
        
        List resultStringArray = new ArrayList();
        
        // While the reader is ready, read each line and add them as a String
        // to the array list.
        while (br.ready()) {
            resultStringArray.add(br.readLine());
        }         
        
        // Close the Buffered Reader
        br.close();
        
        
        /*
         * The result log string is at element position one, not zero because of the 
         * automatic log generation of the date on the first line
         */
        String resultLogString = (String)resultStringArray.get(1);
        
        /*
         * Test to see if the expected log string matches the result log string 
         * that was pulled from the log file.
         */
        assertEquals("INFO: " + expectedLogString, resultLogString);
                
        return;
    }

    /**
     * Test of allStop method, of class GenericScanner.
     */
    public void testAllStop() {
        System.out.println("allStop");
        GenericScanner instance = new GenericScanner();
    }

    /**
     * Test of toString method, of class GenericScanner.
     */
    public void testToString() {
        System.out.println("toString");
        GenericScanner instance = new GenericScanner();
        Map expectedMap = new Hashtable();
        
        /*
         * Populate expected map object with test data
         */
        expectedMap.put(1, "testOne");
        expectedMap.put(2, "testTwo");
        
        /*
         * Set the map to the orders map in the GenericScanner object
         */
        instance.setOrders(expectedMap);
        
        /*
         * Test to make sure expected map was set correctly
         */
        assertEquals(expectedMap, instance.getOrders());
        
        String expectedToString = "Thread: GenericScanner" 
                + System.getProperty("line.separator") + "Orders map has currently: "
                + expectedMap;
        String resultToString = instance.toString();
        
        /*
         * Test to make sure both toString outputs match
         */
        assertEquals(expectedToString, resultToString);
        
        return;
    }
}
