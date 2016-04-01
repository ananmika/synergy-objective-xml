package com.synisys.xml.saxParser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Tatevik
 * since  01-Apr-16.
 */
public class SAXMainHousehold {
    public static final String FILE_PATH = "src/main/resources/dtd/household.xml";

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            HouseholdHandler householdHandler = new HouseholdHandler();
            saxParser.parse(new File(FILE_PATH), householdHandler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
