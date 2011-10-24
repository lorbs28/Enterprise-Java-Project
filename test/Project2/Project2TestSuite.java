/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Project2;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author lorbs28
 */
public class Project2TestSuite extends TestCase {
    
    public Project2TestSuite(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite("Project2TestSuite");
        
        suite.addTest(new GenericScannerTest("testGenericScannerTest"));
        //suite.addTest(new GenericWriterTest("testGenericWriterTest"));

        
        return suite;
    }
    
}
