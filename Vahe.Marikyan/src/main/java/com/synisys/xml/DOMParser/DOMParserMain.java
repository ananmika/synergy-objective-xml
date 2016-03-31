package com.synisys.xml.DOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by VaheMarikyan on 3/31/16.
 */
public class DOMParserMain {

    public static final String FILE_PATH = "src/main/resources/XSD/orders.xml";

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(FILE_PATH));

        NodeList purchaseNodeList = doc.getElementsByTagName("purchase");
        for (int i = 0; i < purchaseNodeList.getLength(); i++) {
            Node purchaseNode = purchaseNodeList.item(i);
            if(purchaseNode.getNodeType() == Node.ELEMENT_NODE){
                Element purchaseElement = (Element) purchaseNode;

                System.out.println("Order Number: " + purchaseElement.getAttribute("purchaseOrderNumber"));
                System.out.println("Order Date: " + purchaseElement.getElementsByTagName("orderDate").item(0).getTextContent());

                NodeList itemsNodeList = purchaseElement.getElementsByTagName("item");
                for (int j = 0; j < itemsNodeList.getLength(); j++) {
                    Node itemNode = itemsNodeList.item(j);
                    if(itemNode.getNodeType() == Node.ELEMENT_NODE){
                        Element itemElement = (Element) itemNode;

                        System.out.println("Item Name: " + itemElement.getElementsByTagName("itemName").item(0).getTextContent());
                    }
                }

                NodeList addressNodeList = purchaseElement.getElementsByTagName("address");
                for (int k = 0; k < addressNodeList.getLength(); k++) {
                    Node addressNode = addressNodeList.item(k);
                    if(addressNode.getNodeType() == Node.ELEMENT_NODE){
                        Element addressElement = (Element) addressNode;

                        if (addressElement.getAttribute("type").equals("shipping")) {
                            System.out.println("Shipping Address: " + addressElement.getElementsByTagName("country").item(0).getTextContent()
                                    + ", " + addressElement.getElementsByTagName("city").item(0).getTextContent()
                                    + ", " + addressElement.getElementsByTagName("street").item(0).getTextContent());
                        }
                    }
                }

                NodeList orderSummaryNodeList = purchaseElement.getElementsByTagName("orderSummary");
                for (int k = 0; k < orderSummaryNodeList.getLength(); k++) {
                    Node orderSummaryNode = orderSummaryNodeList.item(k);
                    if(orderSummaryNode.getNodeType() == Node.ELEMENT_NODE){
                        Element orderSummaryElement = (Element) orderSummaryNode;

                        System.out.println("Total: " + orderSummaryElement.getElementsByTagName("total").item(0).getTextContent()+ "$");
                    }
                }


                System.out.println("======================================");

            }

        }

    }
}
