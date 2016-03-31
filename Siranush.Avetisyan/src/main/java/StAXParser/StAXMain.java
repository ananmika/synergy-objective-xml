package StAXParser;

import common.HouseDetails;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author : Siranush.Avetisyan
 * @since : 27/03/2016
 */
public class StAXMain {
    public static final String INPUT_FILE_PATH = "src/main/resources/DTD/house-selling.xml";
    public static final String OUTPUT_FILE_PATH = "src/main/resources/output.xml";

    public static void main(String[] args) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        List<HouseDetails> houseList = new ArrayList<>();
        HouseDetails houseDetails = new HouseDetails();
        boolean hasActiveSeller = false;
        try {
            XMLEventReader xmlEventReader = factory.createXMLEventReader(new FileReader(INPUT_FILE_PATH));

            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equalsIgnoreCase("house")) {
                        Attribute codeAttr = startElement.getAttributeByName(new QName("code"));
                        houseDetails = new HouseDetails(codeAttr.getValue());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("rooms_number")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        houseDetails.setRoomsCount(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("living_space")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        houseDetails.setLivingSpace(new BigDecimal(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("country")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        houseDetails.getAddress().setCountry(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("city")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        houseDetails.getAddress().setCity(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("state")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        houseDetails.getAddress().setState(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("road")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        houseDetails.getAddress().setRoad(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("postCode")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        houseDetails.getAddress().setPostCode(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("price")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        houseDetails.setPrice(new BigDecimal(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("seller")) {
                        Attribute sellerStatusAttr = startElement.getAttributeByName(new QName("is_active"));
                        if (sellerStatusAttr != null && Objects.equals(sellerStatusAttr.getValue(), "true")) {
                            hasActiveSeller = true;
                        }
                    } else if (hasActiveSeller && startElement.getName().getLocalPart().equalsIgnoreCase("name")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        houseDetails.getActiveSellersNameList().add(xmlEvent.asCharacters().getData());
                        hasActiveSeller = false;
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("house")) {
                        houseList.add(houseDetails);
                    }
                }
            }

            houseList.forEach(System.out::println);

            write(houseList);
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(List<HouseDetails> houseList) throws XMLStreamException, IOException {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventWriter writer = outputFactory.createXMLEventWriter(new FileWriter(OUTPUT_FILE_PATH));
        XMLEventFactory xmlEventFactory = XMLEventFactory.newInstance();

        StartDocument startDocument = xmlEventFactory.createStartDocument("UTF-8", "1.0");
        writer.add(startDocument);

        StartElement startElement = xmlEventFactory.createStartElement("", "", "houses");
        writer.add(startElement);

        for (HouseDetails houseDetails : houseList) {
            Attribute codeAttribute = xmlEventFactory.createAttribute("code", houseDetails.getCode());
            StartElement houseStartElement = xmlEventFactory.createStartElement("", "", "house", Arrays.asList(codeAttribute).iterator(), Arrays.asList().iterator());
            writer.add(houseStartElement);

            StartElement countryStartElement = xmlEventFactory.createStartElement("", "", "country");
            writer.add(countryStartElement);
            Characters countryCharacters = xmlEventFactory.createCharacters(houseDetails.getAddress().getCountry());
            writer.add(countryCharacters);
            EndElement codeEndElement = xmlEventFactory.createEndElement("", "", "country");
            writer.add(codeEndElement);

            for (String activeSellerName : houseDetails.getActiveSellersNameList()) {
                StartElement sellerStartElement = xmlEventFactory.createStartElement("", "", "seller");
                writer.add(sellerStartElement);
                Characters sellerCharacters = xmlEventFactory.createCharacters(activeSellerName);
                writer.add(sellerCharacters);
                EndElement sellerEndElement = xmlEventFactory.createEndElement("", "", "seller");
                writer.add(sellerEndElement);
            }

            StartElement priceStartElement = xmlEventFactory.createStartElement("", "", "price");
            writer.add(priceStartElement);
            Characters priceCharacters = xmlEventFactory.createCharacters(houseDetails.getAddress().getCountry());
            writer.add(priceCharacters);
            EndElement priceEndElement = xmlEventFactory.createEndElement("", "", "price");
            writer.add(priceEndElement);

            EndElement houseEndElement = xmlEventFactory.createEndElement("", "", "house");
            writer.add(houseEndElement);
        }

        EndDocument ed = xmlEventFactory.createEndDocument();
        writer.add(ed);

        writer.flush();
        writer.close();
    }

}
