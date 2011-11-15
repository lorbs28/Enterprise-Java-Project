package Project3;

//be sure to include the following packages
import Project1.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;
import java.util.*;
import java.io.*;
import java.util.logging.*;

/**
 * Class:         OrderSAXParser
 * Function/Duty: This class will read in the XML file and then parse it into objects which are
 *                stored as Order objects inside of a map which will then be passed to the DOM parser
 *                for output processing.
 * 
 * @author Bryan Lor
 */
public class OrderSAXParser extends DefaultHandler
{


    //used to hold the current contents of the xml that is being parsed
    private CharArrayWriter m_oContents;

    
    //insert group of instance variables here that will be used to fill with data as the xml is parsed
    // private Object m_oMyObject;  //for example
    private Order order;
    private ProcessOrder p_order;
    
    private Logger myLogger;
    
    /**
     * Class constructor for this object
     */
    public OrderSAXParser()
    {
        myLogger = Logger.getLogger("mysys");
    }
    
    /**
     * Used to initialize the class and parse the XML
     *
     *  @param String strInXMLPath      full path to the XML
     */
    public void load(String strInXMLPath) throws Exception
    {
        /*
         * Begin input checks on the input XML file name
         * 
         */
        if(strInXMLPath == null || strInXMLPath.length () == 0) {
            myLogger.log(Level.SEVERE, "Filename cannot be empty!");
            throw new OrderCustomException("Filename cannot be empty");
        }
        else if(strInXMLPath.length() > 512) {
            myLogger.log(Level.SEVERE, "Filename cannot exceed 512 characters!");
            throw new OrderCustomException("Filename cannot exceed 512 "
                    + "characters");
        }
        else if(strInXMLPath.indexOf(' ') >= 0) {
            myLogger.log(Level.SEVERE, "Filename cannot contain spaces!");
            throw new OrderCustomException("Filename cannot contain "
                    + "spaces");
        }
        
    	InputStream oInputStream;
        myLogger.log(Level.INFO, "Creating new CharArrayWriter, SAXParser Factory");
        //the following 5 lines of code are canned; they typically not change
        this.m_oContents = new CharArrayWriter();  //create a bucket for the current contents of the XML
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        ParserAdapter pa = new ParserAdapter(sp.getParser());
        myLogger.log(Level.INFO, "Setting content handler");
        pa.setContentHandler(this);

        //instantiate any instance variables here if you will be building some kind of collection
        
        //derive a stream from the file and parse 
        myLogger.log(Level.INFO, "Creating new ProcessOrder object inside OrderSAXParser");
        p_order = new ProcessOrder();
        
        myLogger.log(Level.INFO, "Creating new FileInputStream");
        oInputStream = new FileInputStream(strInXMLPath);
        pa.parse(new InputSource(oInputStream));  //parse() will parse the stream represented by the file in strInXMLPath
    }

    public CharArrayWriter getM_oContents() {
        return m_oContents;
    }

    public void setM_oContents(CharArrayWriter m_oContents) {
        this.m_oContents = m_oContents;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProcessOrder getP_order() {
        return p_order;
    }

    public void setP_order(ProcessOrder p_order) {
        this.p_order = p_order;
    }
    
    public Logger getMyLogger() {
        return myLogger;
    }

    public void setMyLogger(Logger myLogger) {
        this.myLogger = myLogger;
    }
    
    
    
    /**
     * This method is used to determine the start point of an element in an
     * XML stream as it is being parsed; method is called when the parser has identified the beginning of
     * an element; hence, it is called for each and every beginning element found in the XML
     *
     * @param String localName      the name of the element
     * @param Attributes attr               a map-like structure that contains all element attributes
     */
    public void startElement(String namespaceURI, String localName, String qName, Attributes attr) throws SAXException
    {
        String strHold;
        this.m_oContents.reset();  //used to capture the current textual information for an element
        
        /*
         * input checks on localName
         * 
         */
        if(localName == null || localName.length () == 0) {
            myLogger.log(Level.SEVERE, "Local name cannot be empty!");
            try {
                throw new OrderCustomException("Local name cannot be empty.");
            } catch (OrderCustomException ex) {
                myLogger.log(Level.SEVERE, "Exception: Local name is empty.");
            }
        }
        
        //here, we are checking the name of the elements that we are expecting to encounter in the XML; if an 
        //element is encountered, then we will handle it; in our case, we may want to create an object instance and store it
        //since we will be using it; only elements that are currently being acted on are of interest; if we encounter an element
        //that we did not account for, then the data is lost as we will not do anything with it
        
        if(localName.equalsIgnoreCase("NAME"))
        {
            //do my element-specific work here
           myLogger.log(Level.INFO, "Creating Order object");
           order = new Order();
           //the following code will see if the element has associated attribute
           strHold = attr.getValue("custid");
           if(strHold != null && strHold.trim ().length () > 0)
           {
               myLogger.log(Level.INFO, "Setting customer number inside Order object.");
               this.order.setCustomerNumber(Integer.parseInt(strHold));
           }
           
        } else if(localName.equalsIgnoreCase("ITEM"))
        {
           //do my element-specific work here
           //the following code will see if the element has associated attribute
           strHold = attr.getValue("name");
           if(strHold != null && strHold.trim ().length () > 0)
           {
               myLogger.log(Level.INFO, "Setting item name inside Order object.");
               this.order.setItem(strHold);
           }
           
        }
        
    }

    /**
     * This method is used to determine the end point of an element in an
     * XML stream as it is being parsed; it is called for each and every element that is closed in the 
     * XML; the element data was stored in the m_oContents instance variable; we will extract the 
     * accumulated text and place it where it belongs
     */
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException
    {
        String strHold;

        /*
         * input checks on localName
         * 
         */
        if(localName == null || localName.length () == 0) {
            myLogger.log(Level.SEVERE, "Local name cannot be empty!");
            try {
                throw new OrderCustomException("Local name cannot be empty.");
            } catch (OrderCustomException ex) {
                myLogger.log(Level.SEVERE, "Exception: Local name is empty.");
            }
        }
        
        //again, look for the element name that tells us which element just closed
        if(localName.equalsIgnoreCase("ORDER"))
        {
            //here, we extract the accumulated text representing the element data; we then set the information
            //into our instance variable
            myLogger.log(Level.INFO, "Adding finished Order object into the Map, orders, inside ProcessOrder");
            this.p_order.addOrders(this.order.getCustomerNumber(), this.order);
            myLogger.log(Level.INFO, "Setting Order object to null.");
            this.order = null;
            
        }
        else if(localName.equalsIgnoreCase("NAME"))
        {
            strHold = this.m_oContents.toString();
            myLogger.log(Level.INFO, "Setting customer name inside Order object.");
            this.order.setCustomerName(strHold);
            
        }
        else if(localName.equalsIgnoreCase("QUANTITY"))
        {
            strHold = this.m_oContents.toString();
            myLogger.log(Level.INFO, "Setting item quantity inside Order object");
            this.order.setQuantity(Integer.parseInt(strHold));
            
        }
        else if(localName.equalsIgnoreCase("PRICE"))
        {
            strHold = this.m_oContents.toString();
            myLogger.log(Level.INFO, "Setting unit price inside Order object.");
            this.order.setUnitPrice(Double.parseDouble(strHold));
            
        }
    }

    /**
     * Override methods of the DefaultHandler class to gain notification of SAX Events. See
     * org.xml.sax.ContentHandler for all available events
     *
     * This method is used to retrieve the characters in the XML stream
     */
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        this.m_oContents.write(ch, start, length);
    }
    
    /**
     * Method: toString
     * Purpose: This toString incorporates mostly the instance variables
     * Input: logger, Order, ProcessOrder
     * Output: logger, Order, ProcessOrder
     * @return
     */
    public String toString() {
        return "The logger for OrderSAXParser is called: " + myLogger.getName();
    }

}
