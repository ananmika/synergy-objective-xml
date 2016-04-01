package main.java.xPath;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public class XPathMain {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("src/main/webapp/xPath/xPathBook.xml"));

            doc.getDocumentElement().normalize();
            System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());

            NodeList listOfBooks = doc.getElementsByTagName("book");
            int totalBooks = listOfBooks.getLength();
            System.out.println("Total no of books : " + totalBooks);

            for(int i=0; i<listOfBooks.getLength() ; i++) {
                Node firstBookNode = listOfBooks.item(i);
                if(firstBookNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element firstElement = (Element)firstBookNode;
                    System.out.println("Year :"+firstElement.getAttribute("year"));
                    NodeList firstNameList = firstElement.getElementsByTagName("title");
                    Element firstNameElement = (Element)firstNameList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("title : " + ((Node)textFNList.item(0)).getNodeValue().trim());
                }
            }
        } catch (SAXParseException err) {
            System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
            System.out.println(" " + err.getMessage ());
        } catch (SAXException e) {
            Exception x = e.getException ();
            ((x == null) ? e : x).printStackTrace ();
        } catch (Throwable t) {
            t.printStackTrace ();
        }
    }
}
