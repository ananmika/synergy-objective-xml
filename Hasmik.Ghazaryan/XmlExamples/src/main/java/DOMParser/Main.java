package main.java.DOMParser;

import main.java.DOMParser.domain.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        boolean isValid = validateXMLSchema("src/main/resources/poker.xsd","src/main/resources/poker.xml");
        if(isValid){
            System.out.println("poker.xml" + " is valid against " + "poker.xsd");
            try{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(new File("src/main/resources/poker.xml"));
                DocumentTraversal traversal = (DocumentTraversal) doc;
                NodeIterator iterator = traversal.createNodeIterator(doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);

                Table table = new Table(new ArrayList<Player>());
                Player player = null;
                for (Node n = iterator.nextNode(); n != null; n = iterator.nextNode()) {
                    String tagname = ((Element) n).getTagName();
                    if(tagname.equals("player")){
                            player = new Player(new ArrayList<Card>());
                            player.setTurn(Integer.parseInt(((Element) n).getAttribute("turn")));
                            table.getPlayers().add(player);
                    } else if (tagname.equals("card")) {
                        player.getCards().add(new Card(((Element) n).getAttribute("rank"), ((Element) n).getAttribute("suit")));
                    } else if(tagname.equals("cash")){
                        player.setCash(Double.parseDouble(n.getChildNodes().item(0).getNodeValue()));
                    } else if(tagname.equals("action")){
                        Action action = new Action(((Element) n).getAttribute("type"));
                        if(!((Element) n).getAttribute("amount").equals("")){
                            action.setAmount(Double.parseDouble(((Element) n).getAttribute("amount")));
                        }
                        player.setAction(action);
                        // Compare with Combinatios and find the winner!!!
                    }
                }
                System.out.print(player.toString());
            } catch (ParserConfigurationException e) {
                String myMessage = "Serious configuration error in your xml file. ___";
                System.out.println(myMessage + e.getMessage());
                e.printStackTrace();
            } catch (SAXException e) {
                String myMessage = "XML Parse Error. ___";
                System.out.println(myMessage + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("poker.xml" + " is not valid against " + "poker.xsd");
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        } catch (SAXException e) {
            System.out.println("SAXParser Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}
