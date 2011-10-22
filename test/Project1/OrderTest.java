package Project1;


import Project1.Order;
import junit.framework.TestCase;

/**
 * Class:         OrderTest
 * Function/Duty: This class is the test unit for the Order class.
 * @author Bryan Lor
 */
public class OrderTest extends TestCase {
    
    public OrderTest(String testName) {
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
    public void testOrderTest() {
        testCalExtendedPrice();
        testCalHandlingCharge();
        testDisplay();
        testToString();
        
    }
    
    /**
     * Test of calHandlingCharge method, of class Order.
     */
    
    public void testCalExtendedPrice() {
        System.out.println("testing calExtendedPrice");
        Order instance = new Order();
        
        // Set your expected results into the Order object
        instance.setQuantity(5);
        instance.setUnitPrice(10.00);
        
        // Call the calExtendedPrice method inside the Order object
        instance.calExtendedPrice();
        
        /*
         * Set the handling charge to a variable and test to see if the expected
         * result is the same as the actual result
         */        
        double expResult = 50.00;
        double result = instance.getExtendedPrice();
        
        assertEquals(expResult, result, 50.00);
        
        return;
        
    }
    
    /**
     * Test of calHandlingCharge method, of class Order.
     */
    public void testCalHandlingCharge() {
        System.out.println("testing calHandlingCharge");
        Order instance = new Order();
        
        // Create your expected charge range and charge amount arrays
        double[] chargeArray = new double[4];
        int[] chargeAmtArray = new int[4];
        
        chargeArray[0] = 4.00;
        chargeArray[1] = 5.00;
        chargeArray[2] = 6.00;
        chargeArray[3] = 7.00;
        
        chargeAmtArray[0] = 1;
        chargeAmtArray[1] = 4;
        chargeAmtArray[2] = 7;
        chargeAmtArray[3] = 10;
        
        // Set your expected results into the Order object
        instance.setQuantity(5);
        instance.setHChargeAmount(chargeArray);
        instance.setHChargeQuantity(chargeAmtArray);
        
        // Test to see if your expected quantity was set
        assertEquals(5, instance.getQuantity());
        
        // Call the calHandlingCharge method from the Order object
        instance.calHandlingCharge();
        
        /*
         * Set the handling charge to a variable and test to see if the expected
         * result is the same as the actual result
         */        
        double result = instance.getHandlingCharge();
        double expResult = 5.00;
        
        assertEquals(expResult, result, 5.00);
        
        return;
    }

    /**
     * Test of display method, of class Order.
     */
    public void testDisplay() {
        System.out.println("testing display");
        
        Order instance = new Order();
        
        double[] chargeArray = new double[4];
        int[] chargeAmtArray = new int[4];
        
        // Create expected charge amount and charge range arrays
        chargeArray[0] = 4.00;
        chargeArray[1] = 5.00;
        chargeArray[2] = 6.00;
        chargeArray[3] = 7.00;
        
        chargeAmtArray[0] = 1;
        chargeAmtArray[1] = 4;
        chargeAmtArray[2] = 7;
        chargeAmtArray[3] = 10;
        
        // Set your expected results into the Order object
        instance.setCustomerName("John Doe");
        instance.setItem("Thingie");
        instance.setUnitPrice(14.57);
        instance.setQuantity(5);
        instance.setHandlingCharge(5.00);
        instance.setExtendedPrice(72.85);
        instance.setHChargeAmount(chargeArray);
        instance.setHChargeQuantity(chargeAmtArray);
        
        
        // Test to see if what you set in the Order object is correct
        assertEquals("John Doe", instance.getCustomerName());
        assertEquals("Thingie", instance.getItem());
        assertEquals(14.57, instance.getUnitPrice());
        assertEquals(5, instance.getQuantity());
        assertEquals(5.00, instance.getHandlingCharge());
        assertEquals(72.85, instance.getExtendedPrice());
        
                
        String result = instance.display();
        
        /*
         * Test to see if the toString is null or has nothing
         */
        if(result == null || result.trim().length() == 0)
        {
            fail("The display string failed.  Either null or has nothing.");
        }
        
        return;
    }

    /**
     * Test of toString method, of class Order.
     */
    public void testToString() {
        System.out.println("testing Order toString");
        
        Order instance = new Order();
        
        // Set your expected results into the Order object
        instance.setCustomerName("John Doe");
        instance.setItem("Thingie");
        instance.setUnitPrice(14.57);
        instance.setQuantity(5);
        instance.setExtendedPrice(72.85);
        
        
        // Test to see if what you set in the Order object is correct
        assertEquals("John Doe", instance.getCustomerName());
        assertEquals("Thingie", instance.getItem());
        assertEquals(14.57, instance.getUnitPrice());
        assertEquals(5, instance.getQuantity());
        assertEquals(72.85, instance.getExtendedPrice());
        
                
        String result = instance.toString();
        
        /*
         * Test to see if the toString is null or has nothing
         */
        if(result == null || result.trim ().length () == 0)
        {
            fail("The toString failed.  Either null or has nothing.");
        }
        
        return;
    }
}
