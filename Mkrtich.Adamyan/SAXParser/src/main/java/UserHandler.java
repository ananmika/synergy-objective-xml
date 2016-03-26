import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mkrtich.Adamyan on 3/25/2016.
 */
class UserHandler extends DefaultHandler {

    private static final String ROW_BREAKER = "\n*************************************************\n";
    private List<String> textToCopy = new ArrayList<String>();
    private String whiteSpace = "\t";
    private boolean isToBeCopied = false;
    private boolean previousElementIsEnd = false;
    private boolean isDescription = false;

    private String selector = "";

    public UserHandler(AnimalCategories selector) {
        this.selector = selector.getName();
    }

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("animals")) {
            textToCopy.add("<animals>");
        } else if (qName.equalsIgnoreCase("animal")) {
            System.out.println(ROW_BREAKER);
            System.out.println("Start Element :" + qName + "\n");
            String category = attributes.getValue("category");
            isToBeCopied = category.equalsIgnoreCase(selector);
            System.out.println("This Animal is Categorised as : " + category);
        } else if (qName.equalsIgnoreCase("name")) {
            System.out.print("It's name is : ");
        } else if (qName.equalsIgnoreCase("scname")) {
            System.out.print("But Scientifically it is named as : ");
        } else if (qName.equalsIgnoreCase("breeding")) {
            System.out.print("They can have in average : ");
        } else if (qName.equalsIgnoreCase("quantity")) {
            System.out.print("Up to (chlidren) : ");
        } else if (qName.equalsIgnoreCase("period")) {
            System.out.print("In Duration of : ");
        } else if (qName.equalsIgnoreCase("avgweight")) {
            System.out.println("The average weight is (kg) : ");
        } else if (qName.equalsIgnoreCase("male")) {
            System.out.print("For Males : ");
        } else if (qName.equalsIgnoreCase("female")) {
            System.out.print("For Females : ");
        } else if (qName.equalsIgnoreCase("description")) {
            isDescription = true;
            System.out.println("They are considered to be : " + attributes.getValue("diet") + "s");
        } else if (qName.equalsIgnoreCase("dngstatus")) {
            System.out.print("Their Endangerment level is : ");
        }
        if (isToBeCopied) {
            String textToAdd = whiteSpace + "<" + qName;
            int attributesSize = attributes.getLength();
            for (int i = 0; i < attributesSize; i++) {
                textToAdd += " " + attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"";
            }
            textToAdd += ">";
            textToCopy.add(textToAdd);
            whiteSpace += "\t";
            previousElementIsEnd = false;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        textToCopy.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        super.startDocument();
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("animal")) {
            System.out.println("\nEnd Element :" + qName);
            System.out.println(ROW_BREAKER);
        } else if (qName.equalsIgnoreCase("animals")) {
            textToCopy.add("</animals>");
        }
        if (isToBeCopied && !qName.equalsIgnoreCase("animals")) {
            if (previousElementIsEnd) {
                whiteSpace = whiteSpace.substring(0, whiteSpace.length() - 1);
            }
            textToCopy.add(whiteSpace + "</" + qName + ">");
            previousElementIsEnd = true;
        }
    }

    @Override
    public void characters(char ch[],
                           int start, int length) throws SAXException {
        if (!isDescription) {
            System.out.println(new String(ch, start, length));
        }
        isDescription = false;
        if (isToBeCopied) {
            textToCopy.add(whiteSpace + new String(ch, start, length));
            whiteSpace = whiteSpace.substring(0, whiteSpace.length() - 1);
        }
    }

    public List<String> getTextToCopy() {
        return textToCopy;
    }
}
