/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Project2;

import junit.framework.TestCase;

/**
 *
 * @author lorbs28
 */
public class GenericWriterTest extends TestCase {
    
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

    /**
     * Test of run method, of class GenericWriter.
     */
    public void testRun() {
        System.out.println("run");
        GenericWriter instance = new GenericWriter();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeToFile method, of class GenericWriter.
     */
    public void testWriteToFile() {
        System.out.println("writeToFile");
        GenericWriter instance = new GenericWriter();
        instance.writeToFile();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLedgerOutput method, of class GenericWriter.
     */
    public void testGetLedgerOutput() {
        System.out.println("getLedgerOutput");
        GenericWriter instance = new GenericWriter();
        String expResult = "";
        String result = instance.getLedgerOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allStop method, of class GenericWriter.
     */
    public void testAllStop() {
        System.out.println("allStop");
        GenericWriter instance = new GenericWriter();
        instance.allStop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class GenericWriter.
     */
    public void testToString() {
        System.out.println("toString");
        GenericWriter instance = new GenericWriter();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
