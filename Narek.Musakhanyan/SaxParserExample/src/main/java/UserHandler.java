import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Narek.Musakhanyan on 3/26/2016.
 */
public class UserHandler extends DefaultHandler {

    boolean isStadium = false;
    boolean isNickName = false;
    boolean isLeague = false;
    boolean isCoach = false;
    boolean isGoalKeeper = false;
    boolean isPlayer = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("team")) {
            String clubName = attributes.getValue("name");
            System.out.println("Football club : " + clubName);
        } else if (qName.equalsIgnoreCase("stadium")) {
            isStadium = true;
        } else if (qName.equalsIgnoreCase("nickName")) {
            isNickName = true;
        } else if (qName.equalsIgnoreCase("league")) {
            isLeague = true;
        } else if (qName.equalsIgnoreCase("coach")) {
            isCoach = true;
        } else if (qName.equalsIgnoreCase("member")) {
            String player = attributes.getValue("position");
            if (player.equals("goalkeeper")) {
                isPlayer = true;
            }
        } else if (isPlayer && qName.equalsIgnoreCase("player")) {
            isGoalKeeper = true;
            isPlayer = false;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("team")) {
            System.out.println("****************************************\n");
        }
    }

    @Override
    public void characters(char ch[],
                           int start, int length) throws SAXException {
        if (isStadium) {
            System.out.println("Stadium: "
                    + new String(ch, start, length));
            isStadium = false;
        } else if (isNickName) {
            System.out.println("Nick Name: "
                    + new String(ch, start, length));
            isNickName = false;
        } else if (isLeague) {
            System.out.println("League: "
                    + new String(ch, start, length));
            isLeague = false;
        } else if (isCoach) {
            System.out.println("Coach: "
                    + new String(ch, start, length));
            isCoach = false;
        } else if (isGoalKeeper) {
            System.out.println("GoalKeeper: "
                    + new String(ch, start, length));
            isGoalKeeper = false;
        }
    }
}
