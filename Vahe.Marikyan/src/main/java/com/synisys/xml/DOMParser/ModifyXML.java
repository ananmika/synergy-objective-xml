package com.synisys.xml.DOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by VaheMarikyan on 4/1/16.
 */
public class ModifyXML {
    public static void main(String argv[]) {

        try {
            File inputFile = new File("src/main/resources/XSD/orders.xml");
            DocumentBuilderFactory docFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder =
                    docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);


            NodeList purchaseNodeList = doc.getElementsByTagName("purchase");
            NodeList purchasesNodeList = doc.getElementsByTagName("purchases");
            for (int i = 0; i < purchaseNodeList.getLength(); i++) {
                Node purchaseNode = purchaseNodeList.item(i);
                if (purchaseNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element purchaseElement = (Element) purchaseNode;
                    if (purchaseElement.getAttribute("purchaseOrderNumber").equals("UO22222222")) {
                        purchasesNodeList.item(0).removeChild(purchaseNode);
                    }
                    purchaseElement.getElementsByTagName("status").item(0).setTextContent("delivered");
                }
            }

            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
