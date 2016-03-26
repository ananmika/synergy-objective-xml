import java.io.*;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Created by Mkrtich.Adamyan on 3/25/2016.
 */
public class main {

    public static void main(String[] args) throws JDOMException, IOException {
        boolean isCurrency = false;
        boolean isName = false;
        boolean isSurName = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader currencyEvents = factory.createXMLEventReader(new FileReader("src/main/resources/currency.xml"));
            XMLEventReader userEvents = factory.createXMLEventReader(new FileReader("src/main/resources/users.xml"));
            XMLEventReader purchaseEvents = factory.createXMLEventReader(new FileReader("src/main/resources/purchase.xml"));

            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(new File("src/main/resources/purchase.xml"));
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.getChildren("product");
            int id = 0;

            while (currencyEvents.hasNext()) {
                XMLEvent event = currencyEvents.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("name")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            id = Integer.parseInt(attributes.next().getValue());
                            isCurrency = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (isCurrency) {
                            for(Element element : elements) {
                                if(element.getChild("price").getAttribute("currency")!=null && element.getChild("price").getAttribute("currency").getIntValue()==id) {
                                    element.getChild("price").setText(element.getChild("price").getValue() + " " + characters.getData());
                                    element.getChild("price").removeAttribute("currency");
                                }
                            }
                            isCurrency = false;
                        }
                        break;
                }
            }
            while (userEvents.hasNext()) {
                XMLEvent event = userEvents.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("name")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            id = Integer.parseInt(attributes.next().getValue());
                            isName = true;
                        } else if (qName.equalsIgnoreCase("surname")) {
                            isSurName = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (isName) {
                            for(Element element : elements) {
                                if(element.getChild("buyer").getAttribute("id")!=null && element.getChild("buyer").getAttribute("id").getIntValue()==id) {
                                    element.getChild("buyer").setText(element.getChild("buyer").getValue() + " " + characters.getData());
                                }
                            }
                            isName = false;
                        } else if (isSurName) {
                            for(Element element : elements) {
                                if(element.getChild("buyer").getAttribute("id")!=null && element.getChild("buyer").getAttribute("id").getIntValue()==id) {
                                    element.getChild("buyer").setText(element.getChild("buyer").getValue() + " " + characters.getData());
                                    element.getChild("buyer").removeAttribute("id");
                                }
                            }
                            isSurName = false;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        String endQName = endElement.getName().getLocalPart();
                        if (endQName.equalsIgnoreCase("surname")) {
                            isName = false;
                        }
                        break;
                }
            }
            XMLOutputter xmlOutput = new XMLOutputter();
            FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/resources/combination.xml"));
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, fileOutputStream);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}

