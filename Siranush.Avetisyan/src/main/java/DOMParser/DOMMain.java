package DOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : Siranush.Avetisyan
 * @since : 27/03/2016
 */
public class DOMMain {
    public static final String SEPARATOR = ", ";
    public static final String FILE_PATH = "src/main/resources/XSD/aircraft.xml";

    public static void main(String[] args) {

        try {
            File inputFile = new File(FILE_PATH);
            DocumentBuilderFactory dbf
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputFile);
            doc.getDocumentElement().normalize();

            List<Integer> pilotIdList = new ArrayList<>();

            System.out.println("============================");
            Element root = doc.getDocumentElement();
            System.out.println(root.getNodeName());
            System.out.println("============================");

            NodeList personNodeList = doc.getElementsByTagName("person");
            for (int i = 0; i < personNodeList.getLength(); i++) {
                Node personNode = personNodeList.item(i);
                if (personNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element personElement = (Element) personNode;
                    System.out.println("Full Name : " + personElement.getElementsByTagName("fullName").item(0).getTextContent());
                    String personPosition = personElement.getAttribute("position");
                    System.out.println("Position : " + personPosition);
                    if (personPosition.equals("pilot")) {
                        pilotIdList.add(Integer.valueOf(personElement.getAttribute("personId")));
                    }
                    System.out.println("National ID Card : " + personElement.getElementsByTagName("nationIdCard").item(0).getTextContent());
                    System.out.println("BirthDate : " + personElement.getElementsByTagName("birthDate").item(0).getTextContent());
                    System.out.println("Gender : " + personElement.getElementsByTagName("gender").item(0).getTextContent());
                    System.out.println("Email : " + personElement.getElementsByTagName("email").item(0).getTextContent());

                    Node addressNode = personElement.getElementsByTagName("address").item(0);
                    if (addressNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element addressElement = ((Element) addressNode);
                        System.out.println("Address : " + addressElement.getAttribute("country") + SEPARATOR
                                + addressElement.getElementsByTagName("city").item(0).getTextContent() + SEPARATOR
                                + addressElement.getElementsByTagName("street").item(0).getTextContent());
                    }
                    System.out.println("-----------------------------");
                }
            }

            NodeList planeNodeList = doc.getElementsByTagName("plane");
            for (int i = 0; i < planeNodeList.getLength(); i++) {
                Node planeNode = planeNodeList.item(i);
                if (planeNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element planeElement = (Element) planeNode;
                    if (Integer.valueOf(planeElement.getAttribute("rating")) < 1500) {
                        planeElement.getElementsByTagName("mainPilotId").item(0).setTextContent(getAnyPilotId(pilotIdList).toString());
                    }
                }
            }

            transformXML(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer getAnyPilotId(List<Integer> pilotIdList) {
        Random randomGenerator = new Random();
        return pilotIdList.get(randomGenerator.nextInt(pilotIdList.size()));
    }

    public static void transformXML(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(FILE_PATH));
        transformer.transform(source, result);
    }
}
