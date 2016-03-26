import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Narek.Musakhanyan on 3/26/2016.
 */
public class Main {
    public static void main(String args[]){
        try {
            File inputFile = new File("src/main/resources/football.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);
//            FileWriter fileWriter = new FileWriter("InfoDemo.xml");
//            fileWriter.write("");
//            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
