import model.Expression;
import org.junit.Test;
import parsers.stax.ExpressionDataStAXParser;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by anush.hakobyan on 4/1/2016.
 */
public class ExpressionDataStAXParserTest {

    private static final String EXPRESSION_DATA_XML_PATH = "D:/Workspace/XMLParser/src/test/resources/expressionData.xml";

    @Test
    public void testStAxParser() throws FileNotFoundException, XMLStreamException {

        ExpressionDataStAXParser parser = new ExpressionDataStAXParser();

        //Mocks basic data of the first expression form the XML
        Expression expectedExpression = new Expression(7093);
        expectedExpression.setName("1", "expression for indicator[arm]");
        expectedExpression.setName("2", "expression for indicator[ar]");
        expectedExpression.setName("3", "expression for indicator[eng]");
        expectedExpression.setName("4", "expression for indicator[rus]");
        expectedExpression.setValue("commitment + disbursment");

        //Mocks basic data of the second expression form the XML
        Expression expectedExpression1 = new Expression(7498);
        expectedExpression1.setName("1", "negative value[arm]");
        expectedExpression1.setName("2", "negative value[ar]");
        expectedExpression1.setName("3", "negative value[eng]");
        expectedExpression1.setName("4", "negative value[rus]");
        expectedExpression1.setValue("280000-cost");

        //Mocks basic data of the third expression from the XML
        Expression expectedExpression2 = new Expression(7504);
        expectedExpression2.setName("1", "test-expression[arm]");
        expectedExpression2.setName("2", "test-expression[ar]");
        expectedExpression2.setName("3", "test-expression[eng]");
        expectedExpression2.setName("4", "test-expression[rus]");
        expectedExpression2.setValue("(disbursment/commitment)*100");

        List<Expression> expectedExpressionsList = new ArrayList<>();
        expectedExpressionsList.add(expectedExpression);
        expectedExpressionsList.add(expectedExpression1);
        expectedExpressionsList.add(expectedExpression2);

        //Gets expressions with the basic information from the XML
        List<Expression> actualExpressionsList = parser.extractBasicExpressions(EXPRESSION_DATA_XML_PATH);

        assertEquals(expectedExpressionsList, actualExpressionsList);
    }
}
