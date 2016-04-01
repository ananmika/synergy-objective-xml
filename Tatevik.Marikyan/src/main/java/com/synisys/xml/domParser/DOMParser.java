package com.synisys.xml.domParser;

import com.synisys.xml.common.Household;
import com.synisys.xml.common.PersonData;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tatevik.Marikyan
 * @since 01-Apr-16.
 */
public class DOMParser {
    public static final String SEPARATOR = ", ";
    public static final String FILE_PATH = "src/main/resources/XSD/household.xml";

    public static void main(String[] args) {

        Household household = null;
        try {
            File inputFile = new File(FILE_PATH);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(inputFile);
            document.getDocumentElement().normalize();

            NodeList nodes = document.getElementsByTagName(ParserHelper.HOUSEHOLD_TAG);
            int length = nodes.getLength();

            for (int i = 0; i < length; i++){
                Node householdNode = nodes.item(i);
                household = loadHousehold(householdNode);
            }

            System.out.println(household.toString());

        } catch (SAXException  | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Household loadHousehold(Node householdNode){

        PersonData applicant = null;
        List<PersonData> householdMembers = null;
        String description = "";

        NodeList childNodes = householdNode.getChildNodes();
        int length = childNodes.getLength();

        for (int i = 0; i < length; i++){
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE){
                switch (childNode.getNodeName()){
                    case ParserHelper.DESCRIPTION_VALUE_TAG:
                        description = childNode.getTextContent();
                        break;
                    case ParserHelper.PERSON_TAG:
                        applicant = loadPersonData(childNode);
                        break;
                    case ParserHelper.HOUSEHOLD_MEMBERS_TAG:
                        householdMembers = loadHouseholdMembers(childNode);
                        break;
                }
            }
        }

        Household household = new Household(description, applicant, householdMembers);
        return household;
    }

    private static List<PersonData> loadHouseholdMembers(Node householdMembersNode){
        List<PersonData> householdMemberList = new ArrayList<>();

        NodeList childNodes = householdMembersNode.getChildNodes();
        int length = childNodes.getLength();

        for (int i = 0; i < length; i++){
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals(ParserHelper.PERSON_TAG)){
                householdMemberList.add(loadPersonData(childNode));
            }
        }

        return householdMemberList;
    }

    public static PersonData loadPersonData(Node personNode){

        PersonData personData = new PersonData();

        NamedNodeMap attrMap = personNode.getAttributes();

        Integer id = Integer.valueOf(attrMap.getNamedItem(ParserHelper.ID_ATTR).getNodeValue());

        String category = attrMap.getNamedItem(ParserHelper.CATEGORY_ID_ATTR).getNodeValue();

        Node householdHeadNode = attrMap.getNamedItem(ParserHelper.HOUSEHOLD_HEAD_TYPE_ATTR);
        Boolean isHouseholdHead = householdHeadNode != null ? Boolean.parseBoolean(householdHeadNode.getNodeValue()) : null;

        NodeList childNodes = personNode.getChildNodes();
        int length = childNodes.getLength();

        for (int i = 0; i < length; i++){
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE){
                switch (childNode.getNodeName()){
                    case ParserHelper.FIRST_NAME_VALUE_TAG:
                        personData.setFirstName(childNode.getTextContent());
                        break;
                    case ParserHelper.LAST_NAME_VALUE_TAG:
                        personData.setLastName(childNode.getTextContent());
                        break;
                    case ParserHelper.AGE_VALUE_TAG:
                        personData.setAge(Integer.valueOf(childNode.getTextContent()));
                        break;
                    case ParserHelper.GENDER_VALUE_TAG:
                        personData.setGender(childNode.getTextContent());
                        break;
                    case ParserHelper.DOB_VALUE_TAG:
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            personData.setDateOfBirth(formatter.parse(childNode.getTextContent()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case ParserHelper.NATIONALITY_VALUE_TAG:
                        personData.setNationality(childNode.getTextContent());
                        break;
                    case ParserHelper.NIB_VALUE_TAG:
                        personData.setNib(childNode.getTextContent());
                        break;
                }
            }
        }

        personData.setId(id);
        personData.setCategory(category);
        personData.setHouseholdHead(isHouseholdHead);

        return personData;
    }
}
