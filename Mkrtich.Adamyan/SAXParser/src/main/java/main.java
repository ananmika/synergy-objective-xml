import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Mkrtich.Adamyan on 3/25/2016.
 */
public class main {

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/main/resources/Zoo.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler(AnimalCategories.AMPHIBIAN);
            saxParser.parse(inputFile, userhandler);
            FileWriter fileWriter = new FileWriter("src/main/resources/copy_of_Zoo.xml");
            for (String row : userhandler.getTextToCopy()) {
                fileWriter.write(row + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

