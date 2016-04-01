package com.synisys.xml.SAXParser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by VaheMarikyan on 4/1/16.
 */
public class SAXMainOrders {
    public static final String FILE_PATH = "src/main/resources/dtd/orders.xml";

    public static void main(String[] args) {
        SAXParserFactory spf = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = spf.newSAXParser();
            OrderHandler orderHandler = new OrderHandler();
            saxParser.parse(new File(FILE_PATH), orderHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}
