package SAXParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXParserDemo {
    public static void main(String args[]){
        try {
            File inputFile = new File("D:/MyProjects/TrainingDesignPattern/XMLParsers/src/SAXParser/library.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            BookHandler bookHandler = new BookHandler();
            saxParser.parse(inputFile, bookHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
