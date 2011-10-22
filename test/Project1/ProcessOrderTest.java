package Project1;


import Project1.ProcessOrder;
import Project1.Order;
import java.io.*;
import java.util.*;
import junit.framework.TestCase;

/**
 * Class:         ProcessOrderTest
 * Function/Duty: This class is the test unit for the ProcessOrder class
 * @author lorbs28
 */
public class ProcessOrderTest extends TestCase {
    
    public ProcessOrderTest(String testName) {
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
    
    /**
     * Method called from the test suite to run all tests in this class
     */
    public void testProcessOrderTest() throws IOException {
        testReadOrder();
        testReadCharges();
        testCreateOrder();
        testWriteOutput();
        testToString();
        
    }

    /**
     * Test of readOrder method, of class ProcessOrder.
     */
    public void testReadOrder() throws IOException {
        System.out.println("testing readOrder");
        List testOrderInfo = new ArrayList();
        ProcessOrder instance = new ProcessOrder();
        
        BufferedReader  br  = new BufferedReader(new FileReader(
                    "orderin.txt"));
        
        // While the reader is ready, read each line and add them as a String
        // to the array list.
        while (br.ready()) {
            testOrderInfo.add(br.readLine());
        }         
        
        br.close();
        
        // Set the array list for the Process Order object
        instance.setOrderInfo(testOrderInfo);
        
        // Test to see if the array is empty, if it is that means the file was 
        // empty too.
        if (instance.getOrderInfo().isEmpty()) {
            fail("File is empty.");
        }
        
        // Test to see if the size of the arrays are equal to each other
        assertEquals(testOrderInfo.size(), instance.getOrderInfo().size());
        
        
        return;
    }

    /**
     * Test of readCharges method, of class ProcessOrder.
     */
    public void testReadCharges() throws IOException {
        System.out.println("testing readCharges");
        Order orderInstance = new Order();
        ProcessOrder processInstance = new ProcessOrder();
        
        int[]     itemRange = new int[4];
        double[]  itemCharge = new double[4];
        String[]  sarray = null;
        int       counter     = 0;
        
        BufferedReader  br  = new BufferedReader(new FileReader(
                    "charges.dat"));
        
        /* 
         * While the BufferedReader is ready, read each line as a String,
         * then split the String by spaces and parse the first part into an 
         * integer and the second part of the String as a double, then 
         * increase the counter.
         */
        while (br.ready()) {
            String  s  = br.readLine();
            sarray = s.split(" ");
            
            // Fail the test if more than 2 columns of values are passed into
            // the array
            if (sarray.length > 2) {
                fail("Too many columns of values");
            }
            
            itemRange[counter] = Integer.parseInt(sarray[0]);
            itemCharge[counter] = Double.parseDouble(sarray[1]);
            counter++;
        }         
        
        // Close the Buffered Reader
        br.close();
        
        // Test to see the array for containing the lines are empty or not
        // If empty, then the file was also empty with no lines.
        if (sarray == null || sarray.length < 1) {
            fail("File is empty or null.");
        }
        
        // Set the 2 arrays in the Order object for charge quantity and amount
        orderInstance.setHChargeQuantity(itemRange);
        orderInstance.setHChargeAmount(itemCharge);
        
        // Test to see if the array that was set is empty or null
        // If empty, then fail the test.
        if (orderInstance.getHChargeQuantity() == null || 
                orderInstance.getHChargeQuantity().length < 1) {
            fail("File is empty or null.");
        }
        
        // Test to see if the length of both the arrays for item range are the
        // same.
        assertEquals(itemRange.length, 
                orderInstance.getHChargeQuantity().length);
        
        return;
    }

    /**
     * Test of createOrder method, of class ProcessOrder.
     */
    public void testCreateOrder() {
        System.out.println("testing createOrder");
        Order instance = new Order();
        ProcessOrder processInstance = new ProcessOrder();
        
        Map expOrders = new Hashtable();
        List expResultList = new ArrayList();
        
        expResultList.add("Jim 1120 5 20.99 WidgetSorba");
        
        String[] testOrder = ((String) expResultList.get(0)).split("\\s");
        
        instance.setCustomerName(testOrder[0]);
        instance.setCustomerNumber(Integer.parseInt(testOrder[1]));
        instance.setQuantity(Integer.parseInt(testOrder[2]));
        instance.setUnitPrice(Double.parseDouble(testOrder[3]));
        instance.setItem(testOrder[4]);
        
        // Test to see if the set values are set inside the Order object
        assertEquals("Jim", instance.getCustomerName());
        assertEquals(1120, instance.getCustomerNumber());
        assertEquals(5, instance.getQuantity());
        assertEquals(20.99, instance.getUnitPrice());
        assertEquals("WidgetSorba", instance.getItem());
        
        double[] expItemCharge = new double[1];
        int[] expItemRange = new int[1];
        
        expItemCharge[0] = 5.00;
        expItemRange[0] = 4;
        
        // Set the charge amount and quantity
        instance.setHChargeAmount(expItemCharge);
        instance.setHChargeQuantity(expItemRange);
        
        // Make sure what was set here was also set in the Order object
        assertEquals(expItemCharge, instance.getHChargeAmount());
        assertEquals(expItemRange, instance.getHChargeQuantity());
        
        
        // Put the key-value pairs for the Order object into the orders map
        expOrders.put((String)testOrder[0], instance);
        
        // Test to see if the map is empty or has a size of less than 1
        if (expOrders.isEmpty() || expOrders.size() < 1) {
            fail("Map is empty.");
        }
        
        // Test to see if the Order objects match each other
        assertEquals(instance, expOrders.get("Jim"));
        
        return;
        
    }

    /**
     * Test of writeOutput method, of class ProcessOrder.
     */
    public void testWriteOutput() throws IOException {
        System.out.println("testing writeOutput");
        ProcessOrder processOrderInstance = new ProcessOrder();
        Order instance = new Order();
        
        List expResultList = new ArrayList();
        Map expOrders = new Hashtable();
        
       
        
        // Insert an expected value into the List and Map
        expResultList.add("Jim 1120 5 20.99 WidgetSorba");
        
        // Split the String by empty space and insert into an array of type
        // String
        String[] testOrder = ((String) expResultList.get(0)).split("\\s");
        
        // Set the values of the array to the appropriate setters
        instance.setCustomerName(testOrder[0]);
        instance.setCustomerNumber(Integer.parseInt(testOrder[1]));
        instance.setQuantity(Integer.parseInt(testOrder[2]));
        instance.setUnitPrice(Double.parseDouble(testOrder[3]));
        instance.setItem(testOrder[4]);
        
        // Test to see if the set values are set inside the Order object
        assertEquals("Jim", instance.getCustomerName());
        assertEquals(1120, instance.getCustomerNumber());
        assertEquals(5, instance.getQuantity());
        assertEquals(20.99, instance.getUnitPrice());
        assertEquals("WidgetSorba", instance.getItem());
        
        double[] expItemCharge = new double[1];
        int[] expItemRange = new int[1];
        
        expItemCharge[0] = 5.00;
        expItemRange[0] = 4;
        
        // Set the charge amount and quantity
        instance.setHChargeAmount(expItemCharge);
        instance.setHChargeQuantity(expItemRange);
        
        // Make sure what was set here was also set in the Order object
        assertEquals(expItemCharge, instance.getHChargeAmount());
        assertEquals(expItemRange, instance.getHChargeQuantity());
        
        
        // Put the key-value pairs for the Order object into the orders map
        expOrders.put((String)testOrder[0], instance);
        
        // Test to see if the map is empty or has a size of less than 1
        if (expOrders.isEmpty() || expOrders.size() < 1) {
            fail("Map is empty.");
        }
        
        // Test to see if the Order objects match each other
        assertEquals(instance, expOrders.get("Jim"));
        
        // Write to the file
        PrintWriter  pr  = new PrintWriter(new BufferedWriter(
                    new FileWriter("test/Project1/test_orderout.txt")));
        
        Iterator  i  = expOrders.entrySet().iterator();
        
        /* 
         * Loop to get all the values inside the Map then write it out to the 
         * output file
         */
        while (i.hasNext()) {
            Map.Entry  me  = (Map.Entry) i.next();
            Order d   = (Order) me.getValue();
            pr.println(d.display());
        }
        
        // Close the Print Writer
        pr.close();
        
        
        // Read the file to check if what was written to the file is correct
        BufferedReader  br  = new BufferedReader(new FileReader(
                    "test/Project1/test_orderout.txt"));
        
        // While the reader is ready, read each line and add them as a String
        // to the array list.
        while (br.ready()) {
            expResultList.add(br.readLine());
        }         
        
        // Close the Buffered Reader
        br.close();
        
        // Set the array list for the Process Order object
        processOrderInstance.setOrderInfo(expResultList);
        
        // Test to see if the array is empty, if it is that means the file was 
        // empty too.
        if (processOrderInstance.getOrderInfo().isEmpty()) {
            fail("File is empty.");
        }
        
        // Test to see if the size of the arrays are equal to each other
        assertEquals(expResultList.size(), processOrderInstance.getOrderInfo().size());
        
        return;
        
        // test to see if something was written to a test file by writing then 
        // pull from
        // that test file to see if something was written
    }
    
    public void testToString() {
        System.out.println("testing ProcessOrder toString");
        ProcessOrder instance = new ProcessOrder();
        Order orderInstance = new Order();
        
        List expList = new ArrayList();
        Map expMap = new Hashtable();
        
        // Initialize your expected String
        String expString = "Smith 1000 10 10.99 Thingamajigabobber";
        
        // Add the expected String variable to your expected array list
        expList.add(expString);
        
        /*
         * Test to see if the expected list is null or has a size less than 1
         * and if it meets either of those conditions then fail the test
         */
        if (expList == null || expList.size() < 1) {
            fail("The expected list is empty");
        }
        
        /* Process the expected String inside your expected array list 
         * by getting the Object, then casting it back as a String, then split 
         * the String by empty spaces and insert the values into an array of type
         * String
         */
        String[]  sorder  = ((String) expList.get(0)).split("\\s");
        
        /*
         * Set the values in the Order object with the String array, sorder.
         * Expected values are set respectively.
         */
        orderInstance.setCustomerName(sorder[0]);
        orderInstance.setCustomerNumber(Integer.parseInt(sorder[1]));
        orderInstance.setQuantity(Integer.parseInt(sorder[2]));
        orderInstance.setUnitPrice(Double.parseDouble(sorder[3]));
        orderInstance.setItem(sorder[4]);
        
        /*
         * Put the Order object into the expected Map as a value and the key as
         * the customer name
         */
        expMap.put((String) sorder[0], orderInstance);
        
        /*
         * Test to see if the values set are correct as expected
         */
        assertEquals("Smith", orderInstance.getCustomerName());
        assertEquals(1000, orderInstance.getCustomerNumber());
        assertEquals(10, orderInstance.getQuantity());
        assertEquals(10.99, orderInstance.getUnitPrice());
        assertEquals("Thingamajigabobber", orderInstance.getItem());
        
        /*
         * Create your expected item charge and range array
         */
        double[] expItemCharge = new double[1];
        int[] expItemRange = new int[1];
        
        expItemCharge[0] = 7.00;
        expItemRange[0] = 10;
        
        /*
         * Set your item charge and range arrays into the Order object
         */
        orderInstance.setHChargeQuantity(expItemRange);
        orderInstance.setHChargeAmount(expItemCharge);
        
        /*
         * Test to see if the charge amount and quantity are set correctly
         * as expected
         */
        assertEquals(expItemCharge, orderInstance.getHChargeAmount());
        assertEquals(expItemRange, orderInstance.getHChargeQuantity());
        
        /*
         * Set the expected array list and the expecte map into the Process Order
         * object
         */
        instance.setOrderInfo(expList);
        instance.setOrders(expMap);
        
        /*
         * Test to see if the array list and map that were set are set correctly
         * as expected
         */
        assertEquals(expList, instance.getOrderInfo());
        assertEquals(expMap, instance.getOrders());
        
        /*
         * Call the toString method of the Process Order object
         */
        String result = instance.toString();
        
        /*
         * Test to see if the toString returned is null or has a length equal to
         * zero and if it meets any of those conditions then fail the test
         */
        if(result == null || result.trim ().length () == 0)
        {
            fail("The toString failed.  Either null or has nothing.");
        }
        
        // Uncomment next line to test the output of the array and map
        // System.out.println("\nToString for ProcessOrder: \n" + expList + "\n" + expMap);
        
        return;
    }
}
