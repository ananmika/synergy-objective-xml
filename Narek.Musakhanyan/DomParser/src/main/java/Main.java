import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Narek.Musakhanyan on 3/26/2016.
 */
public class Main {
    public static void main(String args[]) {
        File fXmlFile = new File("src/main/java/resource/cars.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            Element root = doc.getDocumentElement();
            System.out.println("Root element : " + root.getNodeName());

            NodeList carList = root.getElementsByTagName("car");
            for (int current = 0; current < carList.getLength(); current++) {
                Node carNode = carList.item(current);
                System.out.println("Car: " + carNode.getAttributes().item(0).getNodeValue());

                Element car = (Element) carNode;

                NodeList predList = car.getElementsByTagName("predecessor");
                for (int i = 0; i < predList.getLength(); i++) {
                    Node predNode = predList.item(i);
                    System.out.println("Predecessor: " + predNode.getAttributes().item(0).getNodeValue());
                    System.out.println("    From: " + car.getElementsByTagName("from").item(0).getTextContent());
                    System.out.println("    To: " + car.getElementsByTagName("to").item(0).getTextContent());
                }
                System.out.println("Founded: " + car.getElementsByTagName("founded").item(0).getTextContent());

                Element founders = (Element) car.getElementsByTagName("founders").item(0);

                NodeList founder = founders.getElementsByTagName("founder");
                for (int j = 0; j < founder.getLength(); j++) {
                    Node founderNode = founder.item(j);
                    System.out.println("Founder: " + founderNode.getTextContent());
                }
                System.out.println("Headquarters: " + car.getElementsByTagName("headquarters").item(0).getTextContent());

                Element owners = (Element) car.getElementsByTagName("owners").item(0);

                NodeList owner = owners.getElementsByTagName("owner");
                for (int k = 0; k < owner.getLength(); k++) {
                    Node ownerNode = owner.item(k);
                    System.out.println("Owner: " + ownerNode.getTextContent());
                    System.out.println("    Percent: " + ownerNode.getAttributes().item(0).getTextContent() + "%");
                }
                System.out.println("Website: " + car.getElementsByTagName("website").item(0).getTextContent());

                System.out.println("**************************************\n");
                Text text = doc.createTextNode("Finish!!!\n");
                car.appendChild(text);
            }
            Main.updateXML(doc, "src/main/java/resource/cars.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateXML(Document document, String path) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            System.out.println("src/main/java/resource/cars.xml");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
