package com.synisys.xml.staxParser;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tatevik
 * since  01-Apr-16.
 */
public class StAXParser {

    public static final String FILE_PATH = "src/main/resources/household.xml";

    public static void main(String[] args) {

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(FILE_PATH));

            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(new File(FILE_PATH));
            Element rootElement = document.getRootElement();

            List<Element> personElements = rootElement.getChildren("householdMembers").get(0).getChildren("person");

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("person")) {

                            QName qNameHouseholdHead = new QName("householdHead");
                            Attribute attributeHouseholdHead = startElement.getAttributeByName(qNameHouseholdHead);

                            if (attributeHouseholdHead != null) {
                                String category = attributeHouseholdHead.getValue();
                                if (category.equalsIgnoreCase("true")) {

                                    for (int i = 0; i < personElements.size(); i++) {

                                        Element personElement = personElements.get(i);
                                        org.jdom2.Attribute householdHeatAttr = personElement.getAttribute("householdHead");

                                        if (householdHeatAttr != null) {
                                            if (householdHeatAttr.getValue().equalsIgnoreCase("true")) {
                                                personElement.removeChild("nationality");
                                                personElement.addContent(new Element("nationality").setText("Armenian"));
                                                break;
                                            }
                                        }

                                    }
                                }
                            }

                        }
                        break;
                }
            }
            XMLOutputter xmlOutput = new XMLOutputter();
            // display xml
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, System.out);
        } catch (XMLStreamException | JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
}
