package Project;

import Project1.Order;
import Project1.ProcessOrder;
import Project3.OrderSAXParser;
import java.io.CharArrayWriter;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import junit.framework.TestCase;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.ParserAdapter;


/**
 * Class:         OrderSAXParserTest
 * Function/Duty: This is the unit test for the OrderSAXParser class
 * 
 * @author Bryan Lor
 */
public class OrderSAXParserTest extends TestCase {
    
    public OrderSAXParserTest(String testName) {
        super(testName);
    }

    OrderSAXParserTest() {
        
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testOrderSAXParserTest() throws Exception {
        testLoad();
        testToString();
    }
    
    /**
     * Test of load method, of class OrderSAXParser.
     */
    public void testLoad() throws Exception {
        System.out.println("testing OrderSAXParser load");
        
        SAXParserFactory expSPF = SAXParserFactory.newInstance();
        
        /*
         * Test to see if the SAXParserFactory object is null.
         */
        if(expSPF == null) {
            fail("SAXParserFactory object is null.");
        }
        
        SAXParser expSP = expSPF.newSAXParser();
        
        /*
         * Test to see if the SAXParser object is null.
         */
        if (expSP == null) {
            fail("SAXParser object is null.");
        }
        
        ParserAdapter expPA = new ParserAdapter(expSP.getParser());
        
        /*
         * Test to see if the ParserAdapter object is null.
         */
        if (expPA == null) {
            fail("ParserAdapter object is null.");
        }
    }

    public void testToString() {
        System.out.println("testing OrderSAXParser toString");
        
        OrderSAXParser instance = new OrderSAXParser();
        
        String expResult = "The logger for OrderSAXParser is called: mysys";
        
        assertEquals(expResult, instance.toString());
    }
}