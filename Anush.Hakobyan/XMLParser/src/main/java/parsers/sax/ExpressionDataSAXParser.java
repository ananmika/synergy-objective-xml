package parsers.sax;


import model.Expression;
import model.Filter;
import model.Participant;
import model.SharingInformation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import parsers.helpers.ParserHelper;
import util.FilterOperator;
import util.FilterType;
import util.ParticipantType;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anush.hakobyan on 3/28/2016.
 */
public class ExpressionDataSAXParser extends DefaultHandler {

    private SAXParser parser = null;

    private ExpressionDataSAXParser handler = null;

    private List<Expression> expressionList = new ArrayList<>();

    private Expression expression;

    private Filter filter;

    private SharingInformation sharingInfo;

    private Participant participant;

    private String elementValue;

    public List<Expression> parseFile(String filePath) {
        File xmlFile = new File(filePath);
        this.handler = new ExpressionDataSAXParser();
        FileInputStream xmlFileStream = null;
        try {
            xmlFileStream = new FileInputStream(xmlFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Expression> processedExpressions = this.handler.processFile(xmlFileStream);

        return processedExpressions;
    }

    private List<Expression> processFile(FileInputStream xmlFileStream) {
        SAXParserFactory spFactory = SAXParserFactory.newInstance();
        spFactory.setNamespaceAware(false);
        spFactory.setValidating(false);
        try {
            this.parser = spFactory.newSAXParser();
            this.parser.parse(xmlFileStream, this);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.expressionList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case ParserHelper.EXPRESSION_TAG:
                Integer expressionId = Integer.parseInt(attributes.getValue(ParserHelper.ID_ATTR));
                this.expression = new Expression(expressionId);
                break;
            case  ParserHelper.FILTER_TAG:
                this.filter = new Filter();
                this.filter.setType(FilterType.getFilterType(attributes.getValue(ParserHelper.FILTER_TYPE_ATTR).toUpperCase()));
                break;
            case ParserHelper.SHARING_INFO_TAG:
                this.sharingInfo = new SharingInformation();
                break;
            case ParserHelper.PARTICIPANT_TAG:
                Integer participantId = Integer.parseInt(attributes.getValue(ParserHelper.ID_ATTR));
                this.participant = new Participant(participantId);
                this.participant.setType(ParticipantType.
                        getUserType(attributes.getValue(ParserHelper.PARTICIPANT_USER_TYPE_ATTR).toUpperCase()));
                break;
            case ParserHelper.EXPRESSION_OWNER_TAG:
                Integer ownerId = Integer.parseInt(attributes.getValue(ParserHelper.ID_ATTR));
                this.participant = new Participant(ownerId);
                this.participant.setType(ParticipantType.
                        getUserType(attributes.getValue(ParserHelper.PARTICIPANT_USER_TYPE_ATTR).toUpperCase()));
                break;
            case ParserHelper.ALIAS_TEXT_TAG:
                this.expression.setLanguageId(attributes.getValue(ParserHelper.LANGUAGE_ID_ATTR));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case ParserHelper.EXPRESSION_TAG:
                this.expressionList.add(this.expression);
                break;
            case ParserHelper.FILTER_TAG:
                this.expression.getFilters().add(this.filter);
                break;
            case ParserHelper.SHARING_INFO_TAG:
                this.expression.setSharingInfo(this.sharingInfo);
                break;
            case ParserHelper.PARTICIPANT_TAG:
                this.sharingInfo.getSharedWithParticipants().add(this.participant);
                break;
            case ParserHelper.PARTICIPANT_NAME_TAG:
                this.participant.setName(this.elementValue);
                break;
            case ParserHelper.EXPRESSION_OWNER_TAG:
                this.sharingInfo.setOwner(this.participant);
                break;
            case ParserHelper.PUBLIC_SHARED_TAG:
                this.sharingInfo.setPublicShared(Boolean.parseBoolean(this.elementValue));
                break;
            case ParserHelper.CATEGORY_ID_TAG:
                this.filter.setCategoryId(Integer.parseInt(this.elementValue));
                break;
            case ParserHelper.FILTER_VALUES_TAG:
                List<String> filterValues = new ArrayList<>(Arrays.asList(this.elementValue.split(",")));
                this.filter.setValues(filterValues);
                break;
            case ParserHelper.ALIAS_TEXT_TAG:
                this.expression.setName(this.elementValue);
                break;
            case ParserHelper.EXPRESSION_VALUE_TAG:
                this.expression.setValue(this.elementValue);
                break;
            case ParserHelper.FILTERS_OPERATOR_TAG:
                this.expression.setFilterOperator(FilterOperator.getFilterOperator(this.elementValue.toUpperCase()));
                break;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {
       this.elementValue = new String(ch, start, length);
    }
}
