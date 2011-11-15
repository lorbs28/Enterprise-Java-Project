package Project;

import Project1.Order;
import Project1.OrderCustomException;
import Project3.OrderDOMParser;
import java.io.File;
import java.util.*;
import java.util.logging.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import junit.framework.TestCase;
import org.w3c.dom.*;


/**
 * Class:         OrderDOMParserTest
 * Function/Duty: This is the unit test for the OrderDOMParser class
 * 
 * @author Bryan Lor
 */
public class OrderDOMParserTest extends TestCase {
    
    public OrderDOMParserTest(String testName) {
        super(testName);
    }

    OrderDOMParserTest() {
        
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testOrderDOMParserTest() throws ParserConfigurationException, OrderCustomException, TransformerException {
        testLoad();
        testGenerateXML();
        testWriteXML();
        testToString();
    }

    /**
     * Test of load method, of class OrderDOMParser.
     */
    public void testLoad() throws ParserConfigurationException {
        System.out.println("testing OrderDOMParser load");
        DocumentBuilderFactory expFactory = DocumentBuilderFactory.newInstance();
        
        /*
         * Test to see if the DocumentBuilderFactory object is null.
         */
        if (expFactory == null) {
            fail("DocumentBuilderFactory object is null.");
        }
        
        DocumentBuilder builder = expFactory.newDocumentBuilder();
        
        /*
         * Test to see if the DocumentBuilder object is null.
         */
        if (builder == null) {
            fail("DocumentBuilder object is null.");
        }
        
        Document expDocOut = builder.newDocument();
        
        
        /*
         * Test to see if the Document object is null.
         */
        if (expDocOut == null) {
            fail("Document object is null.");
        }
                        
    }

    /**
     * Test of generateXML method, of class OrderDOMParser.
     */
    public void testGenerateXML() throws ParserConfigurationException {
        System.out.println("testing generateXML");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document expResultDoc = builder.newDocument();
                
        Node orderNode, itemNode, oNewNode, oNode, oPreviousNode;
        
        Node root = expResultDoc.createElement("report");
        expResultDoc.appendChild(root);
        
        /*
         * Test to see if the root node was created with the proper name
         */
        if(root.getNodeName() != "report") {
            fail("Root node wasn't created and appended.");
        }
        
        orderNode = expResultDoc.createElement("order");
        root.appendChild(orderNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the first child
         * of it's parent.
         */
        if(orderNode.getNodeName() != "order") {
            fail("Order node was not created and appended.");
        }
        
        if(root.getFirstChild() != orderNode) {
            fail("Order node was not created as the first child of root.");
        }
        
        oNewNode = expResultDoc.createElement("name");
        orderNode.appendChild(oNewNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the first child
         * of it's parent.
         */
        if(oNewNode.getNodeName() != "name") {
            fail("Name node was not created and appended.");
        }
        
        if(orderNode.getFirstChild() != oNewNode){
            fail("Name node was not created as the first child of order.");
        }
        
        itemNode = expResultDoc.createElement("item");
        orderNode.appendChild(itemNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the next sibling to
         * the previous node.
         */
        if(itemNode.getNodeName() != "item") {
            fail("Item node was not created and appended.");
        }
        
        if(oNewNode.getNextSibling() != itemNode) {
            fail("Item node was not created as the next sibling to name node.");
        }
        
        oNewNode = expResultDoc.createElement("quantity");
        itemNode.appendChild(oNewNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the first child
         * of it's parent.
         */
        if(oNewNode.getNodeName() != "quantity") {
            fail("Quantity node was not created and appended.");
        }
        
        if(itemNode.getFirstChild() != oNewNode) {
            fail("Quantity node was not created as the first child for item node.");
        }
        
        oPreviousNode = oNewNode;
        oNewNode = expResultDoc.createElement("price");
        itemNode.appendChild(oNewNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the next sibling to
         * the previous node.
         */
        if(oNewNode.getNodeName() != "price") {
            fail("Price node was not created and appended.");
        }
        
        if(oPreviousNode.getNextSibling() != oNewNode) {
            fail("Price node was not created as the next sibling to quantity node.");
        }
        
        oNewNode = expResultDoc.createElement("total");
        orderNode.appendChild(oNewNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the next sibling to
         * the previous node.
         */
        if(oNewNode.getNodeName() != "total") {
            fail("Total node was not created and appended.");
        }
        
        if(itemNode.getNextSibling() != oNewNode) {
            fail("Total node was not created as the next sibling to item node.");
        }
  
    }

    /**
     * Test of writeXML method, of class OrderDOMParser.
     */
    public void testWriteXML() throws OrderCustomException, ParserConfigurationException, TransformerException {
        System.out.println("testing writeXML");
        String expResultPath = "test/Project/test_orderout.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document expResultDoc = builder.newDocument();
        
        /*
         * Test to see if the objects are null
         */
        if(factory == null || builder == null || expResultDoc == null) {
            fail("DocumentBuilderFactory, DocumentBuild, and/or Document objects are null.");
        }
                
        Element orderNode, itemNode, oNewNode, oNode, oPreviousNode;
        Attr attribute;
        Node root = expResultDoc.createElement("report");
        expResultDoc.appendChild(root);
        
        /*
         * Test to see if the root node was created with the proper name
         */
        if(root.getNodeName() != "report") {
            fail("Root node wasn't created and appended.");
        }
        
        orderNode = expResultDoc.createElement("order");
        root.appendChild(orderNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the first child
         * of it's parent.
         */
        if(orderNode.getNodeName() != "order") {
            fail("Order node was not created and appended.");
        }
        
        if(root.getFirstChild() != orderNode) {
            fail("Order node was not created as the first child of root.");
        }
        
        oNewNode = expResultDoc.createElement("name");
        
        attribute = expResultDoc.createAttribute("id");
        
        oNewNode.setAttributeNode(attribute);
        
        if(attribute.getName() != "id") {
            fail("Attribute node not found.");
        }
        
        orderNode.appendChild(oNewNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the first child
         * of it's parent.
         */
        if(oNewNode.getNodeName() != "name") {
            fail("Name node was not created and appended.");
        }
        
        if(orderNode.getFirstChild() != oNewNode){
            fail("Name node was not created as the first child of order.");
        }
        
        itemNode = expResultDoc.createElement("item");
        
        attribute = expResultDoc.createAttribute("name");
        
        itemNode.setAttributeNode(attribute);
        
        if(attribute.getName() != "name") {
            fail("Attribute node not found.");
        }
        
        orderNode.appendChild(itemNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the next sibling to
         * the previous node.
         */
        if(itemNode.getNodeName() != "item") {
            fail("Item node was not created and appended.");
        }
        
        if(oNewNode.getNextSibling() != itemNode) {
            fail("Item node was not created as the next sibling to name node.");
        }
        
        oNewNode = expResultDoc.createElement("quantity");
        itemNode.appendChild(oNewNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the first child
         * of it's parent.
         */
        if(oNewNode.getNodeName() != "quantity") {
            fail("Quantity node was not created and appended.");
        }
        
        if(itemNode.getFirstChild() != oNewNode) {
            fail("Quantity node was not created as the first child for item node.");
        }
        
        oPreviousNode = oNewNode;
        oNewNode = expResultDoc.createElement("price");
        itemNode.appendChild(oNewNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the next sibling to
         * the previous node.
         */
        if(oNewNode.getNodeName() != "price") {
            fail("Price node was not created and appended.");
        }
        
        if(oPreviousNode.getNextSibling() != oNewNode) {
            fail("Price node was not created as the next sibling to quantity node.");
        }
        
        oNewNode = expResultDoc.createElement("total");
        orderNode.appendChild(oNewNode);
        
        /*
         * Test to see if the created node has the right name, then if it is the next sibling to
         * the previous node.
         */
        if(oNewNode.getNodeName() != "total") {
            fail("Total node was not created and appended.");
        }
        
        if(itemNode.getNextSibling() != oNewNode) {
            fail("Total node was not created as the next sibling to item node.");
        }
        
        TransformerFactory oTransformerFactory =  TransformerFactory.newInstance();
        Transformer oTransformer = oTransformerFactory.newTransformer();

        /*
         * Test to see if the objects are null
         */
        if(oTransformerFactory == null || oTransformer == null) {
            fail("TransformerFactory, and/or Transformer objects are null.");
        }
        
        DOMSource oDOMSource = new DOMSource(expResultDoc);
        StreamResult oStreamResult = new StreamResult(new File(expResultPath));

        /*
         * Test to see if the objects are null
         */
        if(oDOMSource == null || oStreamResult == null) {
            fail("DOMSource and/or StreamResult objects are null.");
        }
        
        oTransformer.transform(oDOMSource, oStreamResult);
        
        File resultFile = new File(expResultPath);
        
        /*
         * Test to see if the XML file exists
         */
        if(resultFile == null) {
            fail("File cannot be empty or file does not exist.");
        } else if(resultFile.length() > 1000000000) {
            fail("File cannot exceed 1 Gb in "
                    + "size");
        }
        
    }
    
    public void testToString() {
        System.out.println("testing OrderDOMParser toString");
        
        OrderDOMParser instance = new OrderDOMParser();
        
        String expResult = "The logger for OrderDomParser is called: mysys";
        
        assertEquals(expResult, instance.toString());
    }
}
