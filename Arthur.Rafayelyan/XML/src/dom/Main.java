package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse("C:/Users/rafay/Desktop/XML/src/parser/dom/xmlForDOMParser.xml");

        String titleEl = doc.getDocumentElement().getNodeName();

        System.out.println("titleEl " + " " + titleEl);

        NodeList nodeList = doc.getElementsByTagName("product");

        String title = "";
        int price = 0;
        int amount = 0;
        int departmentId = 0;
        for(int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            title = element.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue();
            price = Integer.parseInt(element.getElementsByTagName("price").item(0).getChildNodes().item(0).getNodeValue());
            amount = Integer.parseInt(element.getElementsByTagName("amount").item(0).getChildNodes().item(0).getNodeValue());
            departmentId = Integer.parseInt(element.getParentNode().getAttributes().getNamedItem("id").getNodeValue());
            System.out.println("Department ID " + " - " + departmentId);
            System.out.println("Title       Price       Amount");
            System.out.println(title + "     " + price + "           " + amount);
        }
    }
}
