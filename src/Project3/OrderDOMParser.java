package Project3;

//be sure to include the following imports for parsing
import Project1.*;
import javax.xml.parsers.*; 
import org.xml.sax.*;
import java.io.*;
import org.w3c.dom.*;

//be sure to include the following imports for writing the xml out to a file
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.util.*;
import java.util.logging.*;

/**
 * Class:         OrderDOMParser
 * Function/Duty: This class will generate the XML for our Order object and then
 *                output our Order objects as a properly formatted XML document.
 * 
 * @author Bryan Lor
 */
public class OrderDOMParser
{
    private Logger myLogger;
    
    /** Creates a new instance of DOMMenuParser */
    public OrderDOMParser ()
    {
        myLogger = Logger.getLogger("mysys");
    }

    public Logger getMyLogger() {
        return myLogger;
    }

    public void setMyLogger(Logger myLogger) {
        this.myLogger = myLogger;
    }
      
    /**
     * Used to initialize the class and create the document builders
     *
     *  @param Map orders      Map with the order objects
     */
    public Document load(Map orders) throws OrderCustomException
    {
        Document oDocOut = null;
        
        /*
         * Begin input check on orders
         * 
         */
        if(orders == null || orders.size() < 1) {
            myLogger.log(Level.SEVERE, "Map cannot be empty!");
            throw new OrderCustomException("Map cannot be empty");
        }
        
        try
        {
            //build a factory and builder then parse the XML
            myLogger.log(Level.INFO, "Creating new DocumentBuilderFactory, DocumentBuilder instances.");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            myLogger.log(Level.INFO, "Instantiating Document object as new document.");
            oDocOut = builder.newDocument();
            
            generateXML(oDocOut, orders);
            writeXML(oDocOut, "orderout_NEW.xml");
        }
        catch(Exception Ex)
        {
            myLogger.log(Level.SEVERE, "Exception error: " + Ex.getMessage());
            Ex.printStackTrace ();
        }
        
        /*
         * Begin output check on oDocOut
         * 
         */
        if(oDocOut == null) {
            myLogger.log(Level.SEVERE, "Document object cannot be null!");
            throw new OrderCustomException("Document object cannot be null.");
        }
        
        return oDocOut;
    }
    
    
    
    
    /**
     * Affect changes to the document in memory by adding new elements; we set text to it in preparation
     * for saving it back to a file
     *
     * @param Document oInDoc
     * @param Map orders
     */
    public void generateXML(Document oInDoc, Map orders) throws OrderCustomException
    {
            /*
             * Begin input check on orders, oInDoc
             * 
             */
            if(orders == null || orders.size() < 1) {
                myLogger.log(Level.SEVERE, "Map cannot be empty!");
                throw new OrderCustomException("Map cannot be empty");
            } else if(oInDoc == null) {
                myLogger.log(Level.SEVERE, "Document object cannot be null!");
                throw new OrderCustomException("Document object cannot be null.");
            }
        
            Element orderNode, itemNode, oNewNode, oNode;
            Attr attribute;
            Iterator  i  = orders.entrySet().iterator();
           
            //get a reference to the root element in my document
            myLogger.log(Level.INFO, "Creating new root element: report.");
            Node root = oInDoc.createElement("report");
            oInDoc.appendChild(root);
            
            /*
             * Loop through the map, orders, and create XML document for each order
             */
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();
                Order d = (Order)me.getValue();
                
                myLogger.log(Level.INFO, "Creating new element: order.");
                orderNode = oInDoc.createElement ("order");
                root.appendChild(orderNode);  //adding the node/element to the root element
            
                myLogger.log(Level.INFO, "Creating new element: name.");
                oNewNode = oInDoc.createElement ("name");
                
                myLogger.log(Level.INFO, "Creating new attribute: customer id.");
                attribute = oInDoc.createAttribute("id");
                attribute.setValue(Integer.toString(d.getCustomerNumber()));
                oNewNode.setAttributeNode(attribute);
                oNewNode.setTextContent(d.getCustomerName());
                orderNode.appendChild(oNewNode);

                myLogger.log(Level.INFO, "Creating new element: item.");
                itemNode = oInDoc.createElement("item");
                
                myLogger.log(Level.INFO, "Creating new attribute: item name.");
                attribute = oInDoc.createAttribute("name");
                attribute.setValue(d.getItem());
                itemNode.setAttributeNode(attribute);
                orderNode.appendChild (itemNode);

                myLogger.log(Level.INFO, "Creating new element: quantity.");
                oNewNode = oInDoc.createElement ("quantity");
                oNewNode.setTextContent(Integer.toString(d.getQuantity()));
                itemNode.appendChild (oNewNode);

                myLogger.log(Level.INFO, "Creating new element: price.");
                oNewNode = oInDoc.createElement("price");
                oNewNode.setTextContent(Double.toString(d.getUnitPrice()));
                itemNode.appendChild(oNewNode);
                
                myLogger.log(Level.INFO, "Creating new element: total.");
                oNewNode = oInDoc.createElement("total");
                oNewNode.setTextContent(Double.toString(d.getExtendedPrice()));
                orderNode.appendChild(oNewNode);
            }

    }
    
    /**
     * We are going to save our edited document to a file
     *
     * @param Document oInDoc       the document to save
     * @param String strInPath            the file path to save
     */
    public void writeXML(Document oInDoc, String strInPath) throws OrderCustomException
    {
            /*
             * Begin input check on orders, oInDoc
             * 
             */
            if(strInPath == null || strInPath.length() == 0) {
                myLogger.log(Level.SEVERE, "Map cannot be empty!");
                throw new OrderCustomException("Map cannot be empty");
            } else if(oInDoc == null) {
                myLogger.log(Level.SEVERE, "Document object cannot be null!");
                throw new OrderCustomException("Document object cannot be null.");
            }
        
        try
        {
            //we use transformers to reformat the document for saving to a file; the following
            //two lines are rather standard
            myLogger.log(Level.INFO, "Creating and running Transformer Factory, beginning XML output.");
            TransformerFactory oTransformerFactory =  TransformerFactory.newInstance();
            Transformer oTransformer = oTransformerFactory.newTransformer();

            //here we are creating a souce and creating a File reference using our file path in 
            //the StreamResult instance; the final transform call streams the document out to the file
            myLogger.log(Level.INFO, "Creating source and File reference using file path.");
            DOMSource oDOMSource = new DOMSource(oInDoc);
            StreamResult oStreamResult = new StreamResult(new File(strInPath));
            
            myLogger.log(Level.INFO, "Streaming document out to the file. File created.");
            oTransformer.transform(oDOMSource, oStreamResult);
        }
        catch(Exception Ex)
        {
            myLogger.log(Level.SEVERE, "Exception error: " + Ex.getMessage());
            Ex.printStackTrace ();
        }
        
        // output checks on XML output file
        File file = new File(strInPath);
        
        /*
         * Begin output checks on the output file
         * 
         */
        if(file == null) {
            myLogger.log(Level.SEVERE, "File cannot be empty!");
            throw new OrderCustomException("File cannot be empty");
        } else if(file.length() > 1000000000) {
            myLogger.log(Level.SEVERE, "File cannot exceed 1 Gb in size!");
            throw new OrderCustomException("File cannot exceed 1 Gb in "
                    + "size");
        

        }
        
    }
    
    /**
     * Method: toString
     * Purpose: This toString incorporates mostly the instance variables
     * Input: logger
     * Output: logger
     * @return
     */
    public String toString() {
        return "The logger for OrderDomParser is called: " + myLogger.getName();
    }
}
