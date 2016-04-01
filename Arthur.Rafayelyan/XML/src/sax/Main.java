package sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // sax parser
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        Handler handler = new Handler();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File("C:/Users/rafay/Desktop/XML/src/parser/sax/xmlForSaxParser.xml"),handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        Map<Integer,String> data = handler.getData();
        for(Map.Entry<Integer,String> dMap: data.entrySet()){
            System.out.println(dMap.getKey() + " - " + dMap.getValue());
        }
    }
}
