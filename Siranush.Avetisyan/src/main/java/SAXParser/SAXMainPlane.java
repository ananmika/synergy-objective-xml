package SAXParser;

import common.Plane;
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
public class SAXMainPlane {
    public static final String FILE_PATH = "src/main/resources/XSD/aircraft.xml";

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            HandlerPlane handlerPlane = new HandlerPlane();
            saxParser.parse(new File(FILE_PATH), handlerPlane);

            List<Plane> planeList = handlerPlane.getPlanes();
            for (Plane plane : planeList) {
                System.out.println("==============================");
                System.out.println(plane.getModel());
                System.out.println(plane.getPilot().getFullName());
                System.out.println(plane.getPilot().getAddress().getCity());
                System.out.println("==============================");
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
