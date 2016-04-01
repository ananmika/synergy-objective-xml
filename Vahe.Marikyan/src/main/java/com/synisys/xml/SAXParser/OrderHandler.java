package com.synisys.xml.SAXParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Created by VaheMarikyan on 4/1/16.
 */
public class OrderHandler extends DefaultHandler {

    private Item item;

    private boolean isItemElement = false;

    private boolean isConditionNew = false;

    private String currentElement = "";

    private int count = 0;

    private Stack<Item> objectStack = new Stack<>();

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();

        System.out.println("Items whose condition is new and price is greater than 10 $");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();

        System.out.println("Found " + count + " item" + (count > 1 ? "s" : ""));
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.currentElement = qName;

        if (qName.equals("item")) {
            isItemElement = true;

            item = new Item();
            this.objectStack.push(item);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if(qName.equals("item")){
            isItemElement = false;

            if(item.getCondition().equals("new") && item.getPrice().compareTo(new BigDecimal(10)) > 0){
                count++;
                System.out.println(item.toString());
                this.objectStack.pop();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if(isItemElement){
            String currentString = new String(ch, start, length).trim();
            if (currentString.length() == 0) {
                return;
            }

            Item item = this.objectStack.peek();
            switch (this.currentElement) {
                case "itemName":
                    item.setName(currentString);
                    break;
                case "price":
                    item.setPrice(new BigDecimal(currentString));
                    break;
                case "quantity":
                    item.setQuantity(Integer.valueOf(currentString));
                    break;
                case "condition":
                    item.setCondition(currentString);
                    break;
                case "size":
                    item.setSize(currentString);
            }
        }
    }
}
