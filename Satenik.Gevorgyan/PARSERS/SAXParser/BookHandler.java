package SAXParser;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookHandler extends DefaultHandler {


    boolean isIsbn = false;
    boolean isTitle = false;
    boolean isPrice = false;
    boolean isRemainingQuantity = false;
    boolean isSold = false;

    boolean isAuthor = false;
    boolean isAuthorName = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("category")) {
            String categoryName = attributes.getValue("title");
            System.out.println("Category : " + categoryName);
        } else if (qName.equalsIgnoreCase("isbn")) {
            isIsbn = true;
        }else if (qName.equalsIgnoreCase("title")) {
            isTitle = true;
        } else if (qName.equalsIgnoreCase("price")) {
            isPrice = true;
        } else if (qName.equalsIgnoreCase("ramainingQuantity")) {
            isRemainingQuantity = true;
        } else if (qName.equalsIgnoreCase("sold")) {
            isSold = true;
        } else if (qName.equalsIgnoreCase("name") ) {
            isAuthorName = true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("category")) {
            System.out.println("****************************************\n");
        }
    }

    @Override
    public void characters(char ch[],
                           int start, int length) throws SAXException {
        if (isIsbn) {
            System.out.println("Book ISBN: "
                    + new String(ch, start, length));
            isIsbn = false;
        } else if (isTitle) {
            System.out.println("Book Title: "
                    + new String(ch, start, length));
            isTitle = false;
        } else if (isPrice) {
            System.out.println("Book Price: "
                    + new String(ch, start, length));
            isPrice = false;
        } else if (isRemainingQuantity) {
            System.out.println("Remaining Quantity: "
                    + new String(ch, start, length));
            isRemainingQuantity = false;
        } else if (isSold) {
            System.out.println("Sold Books: "
                    + new String(ch, start, length));
            isSold = false;
        }else if (isAuthorName) {
            System.out.println("Author: "
                    + new String(ch, start, length));
            isAuthorName = false;
        }
    }
}
