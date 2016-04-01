import helper.ExpressionParserTestHelper;
import model.Participant;
import model.SharingInformation;
import org.junit.Test;
import parsers.dom.ExpressionDataDOMParser;
import util.ParticipantType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by anush.hakobyan on 3/31/2016.
 */
public class ExpressionDataDOMParserTest {
    private static final String EXPRESSION_DATA_XML_PATH = "D:/Workspace/XMLParser/src/test/resources/expressionData.xml";

    @Test
    public void testDOMParser() {
        ExpressionDataDOMParser domParser = new ExpressionDataDOMParser();

        //Mocks the sharing information of the first expression according to data in the xml.
        SharingInformation sharingInfo1 = new SharingInformation();
        sharingInfo1.setPublicShared(false);

        Participant owner = ExpressionParserTestHelper.createParticipant(11, ParticipantType.USER, "Active User");
        sharingInfo1.setOwner(owner);

        List<Participant> participants = new ArrayList<>();
        participants.add(ExpressionParserTestHelper.createParticipant(12, ParticipantType.USER, "Active User2"));
        participants.add(ExpressionParserTestHelper.createParticipant(13, ParticipantType.USER, "Blocked User2"));
        participants.add(ExpressionParserTestHelper.createParticipant(6, ParticipantType.ROLE, "Role 5"));
        participants.add(ExpressionParserTestHelper.createParticipant(7, ParticipantType.GROUP, "Group 10"));
        sharingInfo1.setSharedWithParticipants(participants);

        //Mocks the sharing information of the second expression according to data in the xml.
        SharingInformation sharingInfo2 = new SharingInformation();
        sharingInfo2.setPublicShared(false);

        Participant owner2 = ExpressionParserTestHelper.createParticipant(1, ParticipantType.USER, "Synergy Administrator");
        sharingInfo2.setOwner(owner2);

        List<Participant> participants2 = new ArrayList<>();
        participants2.add(ExpressionParserTestHelper.createParticipant(2, ParticipantType.USER, "Expired User"));
        participants2.add(ExpressionParserTestHelper.createParticipant(2, ParticipantType.ROLE, "Administrator"));
        participants2.add(ExpressionParserTestHelper.createParticipant(1, ParticipantType.GROUP, "Group 1"));
        sharingInfo2.setSharedWithParticipants(participants2);

        List<SharingInformation> expectedSharingInfos = new ArrayList<>();
        expectedSharingInfos.add(sharingInfo1);
        expectedSharingInfos.add(sharingInfo2);

        //Sharing information gotten through xml paring.
        List<SharingInformation> actualSharingInfos = domParser.extractSharingInformations(EXPRESSION_DATA_XML_PATH);

        assertEquals(expectedSharingInfos, actualSharingInfos);
    }
}
