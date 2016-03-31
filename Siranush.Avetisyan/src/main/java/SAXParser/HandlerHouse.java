package SAXParser;

import common.HouseDetails;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Siranush.Avetisyan
 * @since : 27/03/2016
 */
public class HandlerHouse extends DefaultHandler {
    private boolean hasActiveSeller = false;
    private String currentElement = "";
    private Attributes currentAttributes = null;
    private List<HouseDetails> houseList = null;
    private HouseDetails houseDetails = null;

    public List<HouseDetails> getHouseList() {
        return houseList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.currentElement = qName;
        this.currentAttributes = attributes;
        if (qName.equalsIgnoreCase("house")) {
            String houseCode = attributes.getValue("code");
            this.houseDetails = new HouseDetails(houseCode);
            if (this.houseList == null)
                this.houseList = new ArrayList<>();
            System.out.println("House Code : " + houseCode);
        } else if (qName.equalsIgnoreCase("seller")) {
            String sellerStatus = this.currentAttributes.getValue("is_active");
            if (sellerStatus.equals("true")) {
                this.hasActiveSeller = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.currentElement = "";
        this.currentAttributes = null;
        if (qName.equalsIgnoreCase("house")) {
            this.houseList.add(this.houseDetails);
            System.out.println("End Element : " + qName);
            System.out.println("****************************************\n");
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String currentChunk = new String(ch, start, length);

        if (this.currentElement.equalsIgnoreCase("rooms_number")) {
            System.out.println("Rooms Count : " + currentChunk);
            this.houseDetails.setRoomsCount(Integer.valueOf(currentChunk));
        }
        if (this.currentElement.equalsIgnoreCase("living_space")) {
            System.out.println("Living Space : " + currentChunk);
            this.houseDetails.setLivingSpace(new BigDecimal(currentChunk));
        }
        if (this.currentElement.equalsIgnoreCase("price")) {
            System.out.println("Price : " + currentChunk);
            this.houseDetails.setPrice(new BigDecimal(currentChunk));
        }
        if (this.currentElement.equalsIgnoreCase("country")) {
            System.out.println("Country : " + currentChunk);
            this.houseDetails.getAddress().setCountry(currentChunk);
        }
        if (this.currentElement.equalsIgnoreCase("city")) {
            this.houseDetails.getAddress().setCity(currentChunk);
        }
        if (this.currentElement.equalsIgnoreCase("state")) {
            this.houseDetails.getAddress().setState(currentChunk);
        }
        if (this.currentElement.equalsIgnoreCase("road")) {
            this.houseDetails.getAddress().setRoad(currentChunk);
        }
        if (this.currentElement.equalsIgnoreCase("postCode")) {
            this.houseDetails.getAddress().setPostCode(currentChunk);
        }
        if (this.hasActiveSeller && this.currentElement.equalsIgnoreCase("name")) {
            this.houseDetails.getActiveSellersNameList().add(currentChunk);
            this.hasActiveSeller = false;
        }
    }
}
