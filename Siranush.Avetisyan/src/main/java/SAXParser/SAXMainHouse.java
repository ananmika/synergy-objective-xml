package SAXParser;

import common.HouseDetails;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author : Siranush.Avetisyan
 * @since : 27/03/2016
 */
public class SAXMainHouse {
    public static final String FILE_PATH = "src/main/resources/DTD/house-selling.xml";

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            HandlerHouse handlerHouse = new HandlerHouse();
            saxParser.parse(new File(FILE_PATH), handlerHouse);

            List<HouseDetails> houseList = handlerHouse.getHouseList();
            houseList.forEach(System.out::println);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
