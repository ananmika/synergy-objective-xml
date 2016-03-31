package com.synisys.xml.saxParser;

import com.synisys.xml.common.PersonData;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

/**
 * Created by Tatevik
 * since  01-Apr-16.
 */
public class HouseholdHandler extends DefaultHandler {

    private boolean isApplicant = false;
    private PersonData personData;
    private String currentElement = "";
    private boolean isParentPersonElement = false;
    private int count = 0;

    private Stack<PersonData> objectStack = new Stack<>();

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("Members found in XML according to criteria: adult and Female\n");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("We have " + count + " member" + (count > 1 ? "s" : "") + " meeting the above criteria");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.currentElement = qName;

        if (qName.equalsIgnoreCase("person")) {
            this.isParentPersonElement = true;

            if (!attributes.getValue("category").equals("Applicant")) {
                personData = new PersonData();
                this.objectStack.push(personData);
            } else {
                isApplicant = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (qName.equalsIgnoreCase("person")) {
            this.isParentPersonElement = false;
            if (isApplicant) {
                isApplicant = false;
            } else {
                if (personData.getAge().compareTo(18) >= 0 && personData.getGender().equalsIgnoreCase("Female")) {
                    this.count += 1;
                    System.out.println(personData.toString());
                    this.objectStack.pop();
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        String currentString = new String(ch, start, length).trim();
        if (currentString.length() == 0) {
            return;
        }

        if (!isApplicant && this.isParentPersonElement) {
            PersonData personData = this.objectStack.peek();
            switch (this.currentElement) {
                case "firstName":
                    personData.setFirstName(currentString);
                    break;
                case "lastName":
                    personData.setLastName(currentString);
                    break;
                case "age":
                    personData.setAge(Integer.valueOf(currentString));
                    break;
                case "nib":
                    personData.setNib(currentString);
                    break;
                case "gender":
                    personData.setGender(currentString);
            }
        }

    }

}
