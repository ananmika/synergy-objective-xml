package parsers.stax;

import model.Expression;
import parsers.helpers.ParserHelper;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anush.hakobyan on 3/31/2016.
 */
public class ExpressionDataStAXParser {

    public List<Expression> extractBasicExpressions(String filePath) throws FileNotFoundException, XMLStreamException {
        File xmlFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(xmlFile);
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        List<Expression> basicExpressions = this.loadFilters(reader);

        return basicExpressions;
    }

    private List<Expression> loadFilters(XMLStreamReader reader) throws XMLStreamException {
        List<Expression> expressionList = new ArrayList<>();
        Expression expression = null;
        String elementContent = null;
        while (reader.hasNext()) {
            int elementType = reader.next();
            switch (elementType) {
                case XMLStreamConstants.START_ELEMENT :
                    String startElementName = reader.getLocalName();
                    if(ParserHelper.EXPRESSION_TAG.equals(startElementName)) {
                        Integer id = Integer.parseInt(reader.getAttributeValue("", ParserHelper.ID_ATTR));
                        expression = new Expression(id);
                    } else if(ParserHelper.ALIAS_TEXT_TAG.equals(startElementName)) {
                        expression.setLanguageId(reader.getAttributeValue("", ParserHelper.LANGUAGE_ID_ATTR));
                    }
                    break;
                case XMLStreamConstants.CHARACTERS :
                    elementContent = reader.getText().trim();
                    break;
                case XMLStreamConstants.END_ELEMENT :
                    String elementName = reader.getLocalName();
                    if(ParserHelper.EXPRESSION_TAG.equals(elementName)) {
                        expressionList.add(expression);
                    } else if (ParserHelper.ALIAS_TEXT_TAG.equals(elementName)) {
                        expression.setName(elementContent);
                    } else if (ParserHelper.EXPRESSION_VALUE_TAG.equals(elementName)) {
                        expression.setValue(elementContent);
                    }
                    break;
            }
        }

        return expressionList;
    }
}
