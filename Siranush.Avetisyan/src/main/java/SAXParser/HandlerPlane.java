package SAXParser;

import common.Person;
import common.Plane;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

/**
 * @author : Siranush.Avetisyan
 * @since : 27/03/2016
 */
public class HandlerPlane extends DefaultHandler {

    public List<Plane> planes = new ArrayList<>();
    public Map<Integer, Person> personsMap = new HashMap<>();

    private Stack<String> elementStack = new Stack<>();
    private Stack<Object> objectStack = new Stack<>();

    public List<Plane> getPlanes() {
        return planes;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.elementStack.push(qName);
        if (qName.equalsIgnoreCase("plane")) {
            Integer rating = Integer.valueOf(attributes.getValue("rating"));
            Plane plane = new Plane(rating);
            this.objectStack.push(plane);
            this.planes.add(plane);
        } else if (qName.equalsIgnoreCase("person")) {
            Integer personId = Integer.valueOf(attributes.getValue("personId"));
            this.objectStack.push(new Person(personId));
        }
    }

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {

        this.elementStack.pop();

        if (qName.equalsIgnoreCase("person") || qName.equalsIgnoreCase("plane")) {
            Object object = this.objectStack.pop();
            if (qName.equalsIgnoreCase("person")) {
                Person person = (Person) object;
                this.personsMap.put(person.getId(), person);
            }
        }
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {

        String currentChunk = new String(ch, start, length).trim();
        if (currentChunk.length() == 0) {
            return;
        }

        if (currentElement().equalsIgnoreCase("fullName")) {
            Person person = (Person) this.objectStack.peek();
            person.setFullName(currentChunk);
        } else if (currentElement().equalsIgnoreCase("model")) {
            Plane plane = (Plane) this.objectStack.peek();
            plane.setModel(currentChunk);
        } else if (currentElement().equalsIgnoreCase("mainPilotId")) {
            Plane plane = (Plane) this.objectStack.peek();
            Person person = this.personsMap.get(Integer.valueOf(currentChunk));
            plane.setPilot(person);
        } else if (currentElement().equalsIgnoreCase("city") && currentElementParent().equalsIgnoreCase("address")) {
            Person person = (Person) this.objectStack.peek();
            person.getAddress().setCity(currentChunk);
        } else if (currentElement().equalsIgnoreCase("city") && currentElementParent().equalsIgnoreCase("to")) {
            Plane plane = (Plane) this.objectStack.peek();
            plane.getAddressTo().setCity(currentChunk);
        } else if (currentElement().equalsIgnoreCase("city") && currentElementParent().equalsIgnoreCase("from")) {
            Plane plane = (Plane) this.objectStack.peek();
            plane.getAddressFrom().setCity(currentChunk);
        }
    }

    private String currentElement() {
        return this.elementStack.peek();
    }

    private String currentElementParent() {
        if (this.elementStack.size() < 2) {
            return "";
        }
        return this.elementStack.get(this.elementStack.size() - 2);
    }
}
