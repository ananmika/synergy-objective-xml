import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element
        ;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mkrtich.Adamyan on 3/25/2016.
 */
public class main {
    public static void main(String[] args) {

        try {
            File inputFile = new File("src/main/resources/Zoo.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document originalDocument = dBuilder.parse(inputFile);
            originalDocument.getDocumentElement().normalize();


            System.out.println("Root element : " + originalDocument.getDocumentElement().getNodeName());
            NodeList nodeList = originalDocument.getElementsByTagName("animal");
            System.out.println("*****************************");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                System.out.println("\n" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Animal Category : " + eElement.getAttribute("category"));
                    System.out.println("Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Scientific Name : " + eElement.getElementsByTagName("scname").item(0).getTextContent());
                    System.out.println("Breeding : ");
                    System.out.println("Quantity : " + eElement.getElementsByTagName("quantity").item(0).getTextContent());
                    System.out.println("Period : " + eElement.getElementsByTagName("period").item(0).getTextContent());
                    Element description = (Element) eElement.getElementsByTagName("description").item(0);
                    System.out.println("Description : " + description.getAttribute("diet") + description.getTextContent());
                    System.out.println("Average Weight :\n Male : " + eElement.getElementsByTagName("male").item(0).getTextContent()
                            + "\nFemale : " + eElement.getElementsByTagName("female").item(0).getTextContent());
                    if(eElement.getAttribute("category").equalsIgnoreCase(AnimalCategories.INVERTEBRATE.getName())) {
                        Element newElement = originalDocument.createElement("Limbs");
                        newElement.setTextContent(String.valueOf(Integer.valueOf(4)));
                        eElement.appendChild(newElement);
                    }
                }
            }

            Element animal = originalDocument.createElement("animal");
            animal.setAttribute("category", AnimalCategories.INVERTEBRATE.getName());
            Element name = originalDocument.createElement("name");
            name.setTextContent("Added Animal Name");
            Element scname = originalDocument.createElement("scname");
            scname.setTextContent("Added Animal Scname");
            Element breeding = originalDocument.createElement("breeding");
            breeding.setTextContent("Added Animal Breeding Data");
            Element quantity = originalDocument.createElement("breeding");
            quantity.setTextContent("Pups quantity");
            Element period = originalDocument.createElement("period");
            period.setTextContent("pregnancy duration");
            Element avgWeight = originalDocument.createElement("avgWeight");
            avgWeight.setTextContent("Animal AVG weight");
            Element male = originalDocument.createElement("male");
            male.setTextContent("546");
            Element female = originalDocument.createElement("female");
            female.setTextContent("454");
            Element description = originalDocument.createElement("description");
            description.setAttribute("diet", "omnivore");
            description.setTextContent("Some details");
            Element dngStatus = originalDocument.createElement("dngStatus");
            dngStatus.setTextContent("Extremely Endangered");
            animal.appendChild(name);
            animal.appendChild(scname);
            animal.appendChild(breeding);
            breeding.appendChild(quantity);
            breeding.appendChild(period);
            animal.appendChild(avgWeight);
            avgWeight.appendChild(male);
            avgWeight.appendChild(female);
            animal.appendChild(description);
            animal.appendChild(dngStatus);
            originalDocument.getDocumentElement().appendChild(animal);

            saveXML(originalDocument, inputFile.getPath());
            createXML(dBuilder.newDocument());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveXML(Document doc, String path) throws TransformerException {
        TransformerFactory transformerFactory =
                TransformerFactory.newInstance();
        Transformer transformer =
                transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);

    }

    public static void createXML(Document doc) throws TransformerException {
        Element team = doc.createElement("Team");
        doc.appendChild(team);
        ArrayList<String> namesList = new ArrayList<String>();
        namesList.add("John Smith");
        namesList.add("Sarah Connor");
        for (String name : namesList) {
            Element element = doc.createElement("Employee");
            element.setAttribute("IsNew", "False");
            element.setTextContent(name);
            team.appendChild(element);
        }
        Element manager = doc.createElement("manager");
        manager.setTextContent("Bruce Wayne");
        team.appendChild(manager);
        saveXML(doc, "src/main/resources/created.xml");
    }
}

