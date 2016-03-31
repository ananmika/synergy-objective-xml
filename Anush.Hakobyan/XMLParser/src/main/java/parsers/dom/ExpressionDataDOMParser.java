package parsers.dom;

import model.Participant;
import model.SharingInformation;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import parsers.helpers.ParserHelper;
import util.ParticipantType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anush.hakobyan on 3/31/2016.
 */
public class ExpressionDataDOMParser {

    /**
     * Reads XML file and returns sharing informations of the expressions.
     *
     * @param filePath
     *              path to xml file which will be parsed
     *
     * @return list of the sharing informations
     */
    public List<SharingInformation> extractSharingInformations(String filePath) {

        List<SharingInformation> informations = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);
            document.getDocumentElement().normalize();
            NodeList nodes = document.getElementsByTagName(ParserHelper.SHARING_INFO_TAG);
            int length = nodes.getLength();

            informations = new ArrayList<>();
            for(int i = 0; i < length; ++i) {
                Node infoNode = nodes.item(i);
                SharingInformation information = loadSharingInformation(infoNode);
                informations.add(information);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return informations;
    }

    /**
     * Parses XML file and creates sharing information of the appropriate expression.
     *
     * @param infoNode
     *          XML node of the expression which sharing information will be extractec
     * @return sharing information of the expression
     */
    private SharingInformation loadSharingInformation(Node infoNode) {

        boolean isPublicShared = false;
        Participant owner = null;
        List<Participant> participants = null;

        NodeList childNodes = infoNode.getChildNodes();
        int length = childNodes.getLength();
        for(int i = 0; i < length; ++i) {
            Node childNode = childNodes.item(i);

            if(childNode.getNodeType() == Node.ELEMENT_NODE) {
                switch (childNode.getNodeName()) {
                    case ParserHelper.PUBLIC_SHARED_TAG :
                        isPublicShared = Boolean.parseBoolean(childNode.getTextContent());
                        break;
                    case ParserHelper.EXPRESSION_OWNER_TAG :
                        owner = this.loadParticipant(childNode);
                        break;
                    case ParserHelper.PARTICIPANTS_TAG:
                        participants = this.loadParticipants(childNode);
                        break;
                }
            }
        }
        SharingInformation sharingInfo = new SharingInformation(isPublicShared, owner, participants);
        return sharingInfo;
    }

    /**
     * Creates Participant type object by the given xml node.
     *
     * @param participantNode
     *              XML node of the participant
     * @return participant
     */
    private Participant loadParticipant(Node participantNode) {

        NamedNodeMap participantAttributes = participantNode.getAttributes();

        String idAttributeValue = participantAttributes.getNamedItem(ParserHelper.ID_ATTR).getNodeValue();
        Integer id = Integer.parseInt(idAttributeValue);

        String typeAttributeValue = participantAttributes.getNamedItem(ParserHelper.PARTICIPANT_USER_TYPE_ATTR).getNodeValue();
        ParticipantType type = ParticipantType.getUserType(typeAttributeValue);

        NodeList childNodes = participantNode.getChildNodes();
        int length = childNodes.getLength();
        String name = "";
        for(int i = 0; i < length; ++i) {
            Node childNode = childNodes.item(i);
            if(childNode.getNodeType() == Node.ELEMENT_NODE
                    && ParserHelper.PARTICIPANT_NAME_TAG.equals(childNode.getNodeName())) {
                name = childNode.getChildNodes().item(0).getNodeValue();
            }
        }
        Participant participant = new Participant(id, type, name);

        return participant;
    }

    /**
     * Loads participants with whom the expression is shared.
     *
     * @param participantsNode
     *          xml node of the participants, which contains nodes for each single participant
     * @return list of participants
     */
    private List<Participant> loadParticipants(Node participantsNode) {
        List<Participant> participants = new ArrayList<>();
        NodeList childNodes = participantsNode.getChildNodes();
        int length = childNodes.getLength();
        for(int i = 0; i < length; ++i) {
            Node childNode = childNodes.item(i);
            if(childNode.getNodeType() == Node.ELEMENT_NODE
                    && ParserHelper.PARTICIPANT_TAG.equals(childNode.getNodeName())) {
                 participants.add(this.loadParticipant(childNode));
            }
        }
        return participants;
    }

}

