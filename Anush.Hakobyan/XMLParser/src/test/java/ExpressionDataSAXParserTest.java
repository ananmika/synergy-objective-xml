import helper.ExpressionParserTestHelper;
import model.Expression;
import model.Filter;
import model.Participant;
import model.SharingInformation;
import org.junit.Test;
import parsers.sax.ExpressionDataSAXParser;
import util.FilterOperator;
import util.FilterType;
import util.ParticipantType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by anush.hakobyan on 3/29/2016.
 */
public class ExpressionDataSAXParserTest {
    private static final String EXPRESSION_DATA_XML_PATH = "D:/Workspace/XMLParser/src/test/resources/expressionData.xml";

    @Test
    public void testSAXParser() {

        //Mocks the first expression from the XML
        Expression expectedExpression = new Expression(7093);
        expectedExpression.setName("1", "expression for indicator[arm]");
        expectedExpression.setName("2", "expression for indicator[ar]");
        expectedExpression.setName("3", "expression for indicator[eng]");
        expectedExpression.setName("4", "expression for indicator[rus]");
        expectedExpression.setValue("commitment + disbursment");

        Filter filter1 = ExpressionParserTestHelper.createFilter(2318, FilterType.IN, "1");
        Filter filter2 = ExpressionParserTestHelper.createFilter(2495, FilterType.IN, "3207");

        expectedExpression.setFilter(filter1);
        expectedExpression.setFilter(filter2);

        expectedExpression.setFilterOperator(FilterOperator.AND);

        SharingInformation sharingInfo = new SharingInformation();
        sharingInfo.setPublicShared(false);

        Participant owner = ExpressionParserTestHelper.createParticipant(11, ParticipantType.USER, "Active User");
        sharingInfo.setOwner(owner);

        List<Participant> participants = new ArrayList<>();
        participants.add(ExpressionParserTestHelper.createParticipant(12, ParticipantType.USER, "Active User2"));
        participants.add(ExpressionParserTestHelper.createParticipant(13, ParticipantType.USER, "Blocked User2"));
        participants.add(ExpressionParserTestHelper.createParticipant(6, ParticipantType.ROLE, "Role 5"));
        participants.add(ExpressionParserTestHelper.createParticipant(7, ParticipantType.GROUP, "Group 10"));
        sharingInfo.setSharedWithParticipants(participants);

        expectedExpression.setSharingInfo(sharingInfo);

        ExpressionDataSAXParser parser = new ExpressionDataSAXParser();
        List<Expression> expressionList =  parser.parseFile(EXPRESSION_DATA_XML_PATH);

        //Gets the first expression to check with the mocked one.
        Expression currentExpression = expressionList.get(0);

        System.out.println(expectedExpression.toString());
        System.out.println(currentExpression.toString());

        assertEquals(expectedExpression, currentExpression);
    }



}
