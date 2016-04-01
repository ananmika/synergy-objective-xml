package main.java.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public class SaxMain {
    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SaxHandler handler = new SaxHandler();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File("src/main/webapp/sax/saxParser.xml"),handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        Map<Integer,String> data = handler.getData();
        for(Map.Entry<Integer,String> dMap: data.entrySet()){
            System.out.println(dMap.getKey() + " - " + dMap.getValue());
        }
    }
}
