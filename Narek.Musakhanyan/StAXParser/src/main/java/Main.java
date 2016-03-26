import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Created by Narek.Musakhanyan on 3/26/2016.
 */
public class Main {
    public static void main(String[] args) throws JDOMException, IOException {
        boolean isStadium = false;
        boolean isNickName = false;
        boolean isLeague = false;
        boolean isCoach = false;
        boolean isGoalKeeper = false;
        boolean isPlayer = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("src/main/resources/football.xml"));
            Document document = null;

                SAXBuilder saxBuilder = new SAXBuilder();
                document = saxBuilder.build(new File("src/main/resources/football.xml"));
                Element rootElement = document.getRootElement();
                List<Element> footballElements = new ArrayList<Element>();
                footballElements = rootElement.getChildren("team");
            while(eventReader.hasNext()){
                XMLEvent eventFootball = eventReader.nextEvent();

                switch(eventFootball.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = eventFootball.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("team")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String clubName = attributes.next().getValue();
                            System.out.println("Football club : " + clubName);
                            for(int i=0; i < footballElements.size(); i++){
                                footballElements.get(i).removeChild("league");
                            }
                        } else if (qName.equalsIgnoreCase("stadium")) {
                            isStadium = true;
                        } else if (qName.equalsIgnoreCase("nickName")) {
                            isNickName = true;
                        } else if (qName.equalsIgnoreCase("league")) {
                            isLeague = true;
                        } else if (qName.equalsIgnoreCase("coach")) {
                            isCoach = true;
                        } else if (qName.equalsIgnoreCase("member")) {
                            String player = ((Attribute)startElement.getAttributes().next()).getValue();
                            if (player.equals("goalkeeper")) {
                                isPlayer = true;
                            }
                        } else if (isPlayer && qName.equalsIgnoreCase("player")) {
                            isGoalKeeper = true;
                            isPlayer = false;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = eventFootball.asCharacters();
                        if (isStadium) {
                            System.out.println("Stadium: "
                                    + characters.getData());
                            isStadium = false;
                        } else if (isNickName) {
                            System.out.println("Nick Name: "
                                    + characters.getData());
                            isNickName = false;
                        } else if (isLeague) {
                            System.out.println("League: "
                                    + characters.getData());
                            isLeague = false;
                        } else if (isCoach) {
                            System.out.println("Coach: "
                                    + characters.getData());
                            isCoach = false;
                        } else if (isGoalKeeper) {
                            System.out.println("GoalKeeper: "
                                    + characters.getData());
                            isGoalKeeper = false;
                        }
                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = eventFootball.asEndElement();
                        if(endElement.getName().getLocalPart().equalsIgnoreCase("team")){
                            System.out.println("End Element : team");
                            System.out.println("****************************************\n");
                        }
                        break;
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/resources/football.xml"));
            XMLOutputter xmlOutputter = new XMLOutputter();
            try {
                xmlOutputter.output(document, fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}