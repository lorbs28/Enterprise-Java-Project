package Project;

import Project2.GenericWriter;
import java.io.*;
import java.util.*;
import junit.framework.TestCase;

/**
 * Class:         GenericWriterTest
 * Function/Duty: This is the unit test for the GenericWriter class
 * 
 * @author Bryan Lor
 */

public class GenericWriterTest extends TestCase {
     
    public GenericWriterTest() {
        
    }
    
    public GenericWriterTest(String testName) {
        
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
    
    public void testGenericWriterTest()throws FileNotFoundException, IOException {
        testWriteToFile();
        testToString();
    }

    /**
     * Test of writeToFile method, of class GenericWriter.
     */
    public void testWriteToFile() throws IOException {
        System.out.println("testing writeToFile");
        GenericWriter instance = new GenericWriter();
        List expectedResultList = new ArrayList();
        String expectedString = "Customer: Jim\n" 
                + "Item Ordered: WidgetSorbaThingers \n" 
                + "Unit Price: $20.99\n"
                + "Total: $104.95\n"
                + "Plus a $5.0 processing charge\n"
                + "Grand Total: 109.95";
        
        instance.setLedgerOutput(expectedString);
        
        assertEquals(expectedString, instance.getLedgerOutput());
        
        PrintWriter  pr  = new PrintWriter(new BufferedWriter(
                    new FileWriter("test/Project/test_orderout_thread.txt")));
        
        pr.print(expectedString);
        
        pr.close();
        
        BufferedReader  br  = new BufferedReader(new FileReader(
                    "test/Project/test_orderout_thread.txt"));
        
        // While the reader is ready, read each line and add them as a String
        // to the array list.
        while (br.ready()) {
            expectedResultList.add(br.readLine());
        }
        
        br.close();
        
        assertEquals("Customer: Jim", expectedResultList.get(0));
        
        return;
    }

    /**
     * Test of toString method, of class GenericWriter.
     */
    public void testToString() {
        System.out.println("testing GenericWriter toString");
        GenericWriter instance = new GenericWriter();
        String expLedgerOutput = "Customer: Jim\n" 
                + "Item Ordered: WidgetSorbaThingers \n" 
                + "Unit Price: $20.99\n"
                + "Total: $104.95\n"
                + "Plus a $5.0 processing charge\n"
                + "Grand Total: 109.95";
        String expResult = "Thread: Generic Writer" + System.getProperty("line.separator")
                + "Ledger Output: Customer: Jim\n" 
                + "Item Ordered: WidgetSorbaThingers \n" 
                + "Unit Price: $20.99\n"
                + "Total: $104.95\n"
                + "Plus a $5.0 processing charge\n"
                + "Grand Total: 109.95";
        
        instance.setLedgerOutput(expLedgerOutput);
        
        assertEquals(expLedgerOutput, instance.getLedgerOutput());
        
        String result = instance.toString();
        
        /*
         * Determine if the toString is null or has a length of zero
         */
        if(result == null || result.trim ().length () == 0)
        {
            fail("The toString failed.  Either null or has nothing.");
        }
        
        assertEquals(expResult, result);
        
        return;
    }
}
