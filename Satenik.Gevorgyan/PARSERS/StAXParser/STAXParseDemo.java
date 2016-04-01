package STAXParser;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class STAXParseDemo {
        public static final String INPUT_FILE_PATH = "D:/MyProjects/TrainingDesignPattern/XMLParsers/src/STAXParser/shop.xml";
        public static final String OUTPUT_FILE_PATH = "D:/MyProjects/TrainingDesignPattern/XMLParsers/src/STAXParser/output.xml";

    public static void main(String[] args) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        List<Item> selectedItems = new ArrayList<>();
        Item item = new Item();
        Integer totalAmount = 0;
        try {
            XMLEventReader xmlEventReader = factory.createXMLEventReader(new FileReader(INPUT_FILE_PATH));

            while (xmlEventReader.hasNext()) {

                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equalsIgnoreCase("good")) {
                        Attribute goodsName = startElement.getAttributeByName(new QName("goodName"));
                        item.setName(goodsName.getValue());
                        if(item.getName() != null && item.getName().equals("Milk")){
                            item.setQuantity(2);
                        }else{
                            item.setQuantity(1);
                        }
                    }  else if(startElement.getName().getLocalPart().equalsIgnoreCase("price")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        item.setPrice(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }else if (startElement.getName().getLocalPart().equalsIgnoreCase("productor")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        Attribute productorName = startElement.getAttributeByName(new QName("productorName"));
                        if(productorName.getValue() != null && productorName.getValue().equals("Tamara")){
                            item = new Item();
                        }
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("good")) {
                        selectedItems.add(item);
                    }
                }
            }
            write(selectedItems);
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(List<Item> items) throws XMLStreamException, IOException {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventWriter writer = outputFactory.createXMLEventWriter(new FileWriter(OUTPUT_FILE_PATH));
        XMLEventFactory xmlEventFactory = XMLEventFactory.newInstance();

        StartDocument startDocument = xmlEventFactory.createStartDocument("UTF-8", "1.0");
        writer.add(startDocument);

        StartElement startElement = xmlEventFactory.createStartElement("", "", "goods");
        writer.add(startElement);

        for (Item item : items) {
            Attribute goodsNameAttribute = xmlEventFactory.createAttribute("goodName", item.getName());
            StartElement goodsStartElement = xmlEventFactory.createStartElement("", "", "good", Arrays.asList(goodsNameAttribute).iterator(), Arrays.asList().iterator());
            writer.add(goodsStartElement);


            StartElement priceStartElement = xmlEventFactory.createStartElement("", "", "price");
            writer.add(priceStartElement);
            Characters priceCharacters = xmlEventFactory.createCharacters(item.getPrice().toString());
            writer.add(priceCharacters);
            EndElement priceEndElement = xmlEventFactory.createEndElement("", "", "price");
            writer.add(priceEndElement);

            if(item.getQuantity() != null && item.getQuantity() > 0) {
                StartElement quantityStartElement = xmlEventFactory.createStartElement("", "", "quantity");
                writer.add(quantityStartElement);
                Characters quantityCharacters = xmlEventFactory.createCharacters(item.getQuantity().toString());
                writer.add(quantityCharacters);
                EndElement quantityEndElement = xmlEventFactory.createEndElement("", "", "quantity");
                writer.add(quantityEndElement);
            }

            StartElement totalStartElement = xmlEventFactory.createStartElement("", "", "totalAmount");
            writer.add(totalStartElement);
            Characters totalCharacters = xmlEventFactory.createCharacters(item.getTotalAmount().toString());
            writer.add(totalCharacters);
            EndElement totalEndElement = xmlEventFactory.createEndElement("", "", "totalAmount");
            writer.add(totalEndElement);

            EndElement houseEndElement = xmlEventFactory.createEndElement("", "", "goods");
            writer.add(houseEndElement);
        }

        EndDocument ed = xmlEventFactory.createEndDocument();
        writer.add(ed);

        writer.flush();
        writer.close();
    }

}
