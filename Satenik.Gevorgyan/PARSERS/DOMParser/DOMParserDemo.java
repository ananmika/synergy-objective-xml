package DOMParser;


import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMParserDemo {

    public static void main(String args[]) {
        File  fXmlFile = new File("D:/MyProjects/TrainingDesignPattern/XMLParsers/src/DOMParser/library.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            Element root = doc.getDocumentElement();
            System.out.println("Root element : " + root.getNodeName());

            NodeList categoryList = root.getElementsByTagName("category");
            for (int current = 0; current < categoryList.getLength(); current++) {
                Node categoryNode = categoryList.item(current);
                System.out.println("Category");
                System.out.println("Name: " + categoryNode.getAttributes().item(0).getNodeValue());
                System.out.println("Number: " + categoryNode.getAttributes().item(1).getNodeValue());
                Element category = (Element) categoryNode;
                NodeList books = category.getElementsByTagName("books");

                for (int j = 0; j < books.getLength(); j++) {

                    Node booksNode = books.item(j);
                    Element booksElement = (Element) booksNode;

                    NodeList bookList = booksElement.getElementsByTagName("book");
                    for (int i = 0; i < bookList.getLength(); i++) {
                        Node bookNode = bookList.item(i);
                        Element book = (Element) bookNode;

                        System.out.println("Book details ");
                        System.out.println("available: " + bookNode.getAttributes().item(0).getNodeValue());

                        System.out.println("ISBN: " + book.getElementsByTagName("isbn").item(0).getTextContent());
                        System.out.println("Title: " + book.getElementsByTagName("title").item(0).getTextContent());
                        System.out.println("Sub Title: " + book.getElementsByTagName("subTitle").item(0).getTextContent());
                        System.out.println("Price: " + book.getElementsByTagName("price").item(0).getTextContent());
                        System.out.println("Publisher House: " + book.getElementsByTagName("publisherHouse").item(0).getTextContent());
                        System.out.println("Publishing Year: " + book.getElementsByTagName("publishingYear").item(0).getTextContent());
                        System.out.println("Printed Place: " + book.getElementsByTagName("printedPlace").item(0).getTextContent());
                        System.out.println("Illustrator: " + book.getElementsByTagName("illustrator").item(0).getTextContent());
                        System.out.println("Ramaining Quantity: " + book.getElementsByTagName("ramainingQuantity").item(0).getTextContent());
                        System.out.println("Sold: " + book.getElementsByTagName("sold").item(0).getTextContent());


                        NodeList authorsList = book.getElementsByTagName("authors");
                        for (int k = 0; k < authorsList.getLength(); k++) {
                            Node authorsNode = authorsList.item(k);

                            Element authors = (Element) authorsNode;
                            NodeList authorList = authors.getElementsByTagName("author");
                            for (int index = 0; index < bookList.getLength(); index++) {
                                Node authorNode = authorList.item(index);
                                Element author = (Element) authorNode;
                                System.out.println("Book authors Details ");
                                System.out.println("Name: " + author.getElementsByTagName("name").item(0).getTextContent());
                                System.out.println("Birth Date: " + author.getElementsByTagName("birthDate").item(0).getTextContent());
                                System.out.println("Nationality: " + author.getElementsByTagName("nationality").item(0).getTextContent());


                            }

                        }

                    }

                }

                System.out.println("**************************************\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
