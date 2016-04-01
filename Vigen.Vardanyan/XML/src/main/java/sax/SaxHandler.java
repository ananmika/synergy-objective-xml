package main.java.sax;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public class SaxHandler extends DefaultHandler {
    private Map<Integer,String> data = new HashMap<>();
    private int id;
    private String element;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing document....");
    }
    @Override
    public void endDocument() throws SAXException {
        System.out.println("End document....");
    }

    /**
     * @param attributes return all information about that tag
    * */
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        element = qName;
        if(element.equals("product")) {
            id = Integer.parseInt(attributes.getValue(0));
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        element = "";
        id = -1;
    }

    @Override
    public void characters(char []ch,int start,int end){
        if(element.equals("title")){
            String titleStr = new String(ch,start,end);
            data.put(id,titleStr);
        }
    }

    public Map<Integer, String> getData() {
        return data;
    }
}
