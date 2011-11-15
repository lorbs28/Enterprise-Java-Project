package Project;


import java.io.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Class:         OrderTestSuite
 * Function/Duty: This the test suite for:
 *                  - ProcessOrderTest
 *                  - OrderTest
 *                  - OrderDriverTest
 * @author Bryan Lor
 */
public class OrderTestSuite extends TestCase {
    
    public OrderTestSuite(String testName) {
        super(testName);
    }
    
    /**
     * Method calls the respective methods from the other test classes to run
     * the tests as a test suite
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("OrderTestSuite");
        
            
            suite.addTest(new ProcessOrderTest("testProcessOrderTest"));
            suite.addTest(new OrderTest("testOrderTest"));
            suite.addTest(new OrderDriverTest("testOrderDriverTest"));
        
        
        return suite;
    }
}
