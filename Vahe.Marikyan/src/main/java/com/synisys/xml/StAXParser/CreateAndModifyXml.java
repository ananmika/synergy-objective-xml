package com.synisys.xml.StAXParser;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Created by VaheMarikyan on 4/2/16.
 */
public class CreateAndModifyXml {

    public static final String FILE_PATH = "src/main/resources/StAX/order.xml";

    public static void main(String[] args) {

        CreateAndModifyXml createAndModifyXml = new CreateAndModifyXml();

        createAndModifyXml.createXML(FILE_PATH);
        createAndModifyXml.modifyXML(FILE_PATH);
    }

    private void createXML(String filePath) {
        try {
            StringWriter stringWriter = new StringWriter();

            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(new FileOutputStream(filePath), "UTF-8");

            xMLStreamWriter.writeStartDocument();
            xMLStreamWriter.writeStartElement("purchases");

            xMLStreamWriter.writeStartElement("purchase");

            xMLStreamWriter.writeStartElement("status");
            xMLStreamWriter.writeCharacters("shipped");
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("trackingNumber");
            xMLStreamWriter.writeCharacters("LT6784868324CK");
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("items");

            xMLStreamWriter.writeStartElement("item");
            xMLStreamWriter.writeAttribute("id", "1");

            xMLStreamWriter.writeStartElement("itemName");
            xMLStreamWriter.writeCharacters("item 1");
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("size");
            xMLStreamWriter.writeCharacters("M");
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("quantity");
            xMLStreamWriter.writeCharacters("2");
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("condition");
            xMLStreamWriter.writeCharacters("new");
            xMLStreamWriter.writeEndElement();

            // end of item
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("item");
            xMLStreamWriter.writeAttribute("id", "2");

            xMLStreamWriter.writeStartElement("itemName");
            xMLStreamWriter.writeCharacters("item 2");
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("size");
            xMLStreamWriter.writeCharacters("s");
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("quantity");
            xMLStreamWriter.writeCharacters("1");
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("condition");
            xMLStreamWriter.writeCharacters("used");

            xMLStreamWriter.writeEndElement();

            // end of item
            xMLStreamWriter.writeEndElement();

            // end of items
            xMLStreamWriter.writeEndElement();

            // end of purchases
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeEndDocument();

            xMLStreamWriter.flush();
            xMLStreamWriter.close();

            String xmlString = stringWriter.getBuffer().toString();

            stringWriter.close();

            System.out.println(xmlString);

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifyXML(String filePath) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(filePath));
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(new File(filePath));
            Element rootElement = document.getRootElement();
            List<Element> purchaseElements = rootElement.getChild("purchase").getChildren("items");
            Element itemToRemove = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("items")) {
                            for (int i = 0; i < purchaseElements.size(); i++) {
                                Element itemsElement = purchaseElements.get(i); // items node
                                List<Element> itemElements = itemsElement.getChildren("item");
                                for (int j = 0; j < itemElements.size(); j++) {
                                    Element itemElement = itemElements.get(j);
                                    if (itemElement.getAttribute("id").getValue().equalsIgnoreCase("2")) {
                                        itemElement.removeChild("condition");
                                        itemElement.addContent(new Element("condition").setText("new"));
                                        itemElement.removeChild("quantity");
                                        itemElement.addContent(new Element("quantity").setText("5"));
                                        break;
                                    } else if (itemElement.getAttribute("id").getValue().equalsIgnoreCase("1")) {
                                        itemToRemove = itemElement;
                                    }
                                }
                                itemsElement.removeContent(itemToRemove);
                            }
                        }
                }
            }
            XMLOutputter xmlOutput = new XMLOutputter();
            // display xml
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, System.out);
        } catch (XMLStreamException  | JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
}
