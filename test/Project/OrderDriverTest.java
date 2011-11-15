package Project;


import java.io.*;
import java.util.*;
import junit.framework.TestCase;

/**
 * Class:         OrderDriverTest
 * Function/Duty: This class is the test unit for the OrderDriver class. 
 * @author Bryan Lor
 */
public class OrderDriverTest extends TestCase {
    
    public OrderDriverTest(String testName) {
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
    public void testOrderDriverTest() throws IOException {
        System.out.println("TESTING: OrderDriverTest");
        testLoadProperties();
        
    }
    
    /**
     * Test of loadProperties method, of class OrderDriver.
     */
    public void testLoadProperties() throws IOException {
        System.out.println("testing loadProperties");
        
        System.getProperties().load(new FileInputStream("properties.ini"));
        
        
        assertEquals("orderin.txt", System.getProperty("input.filename"));
        assertEquals("orderout.txt", System.getProperty("output.filename"));
        
        return;
    }
}
