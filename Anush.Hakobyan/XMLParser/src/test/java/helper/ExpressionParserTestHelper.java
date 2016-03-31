package helper;

import model.Filter;
import model.Participant;
import model.SharingInformation;
import util.FilterType;
import util.ParticipantType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anush.hakobyan on 3/30/2016.
 */
public class ExpressionParserTestHelper {

    public static Participant createParticipant(Integer id, ParticipantType type, String name) {
        Participant participant = new Participant(id);
        participant.setType(type);
        participant.setName(name);

        return participant;
    }

    public static Filter createFilter(Integer categoryId, FilterType type, String values) {
        Filter filter = new Filter();
        filter.setCategoryId(categoryId);
        filter.setType(type);
        List<String> filterValues = new ArrayList<>(Arrays.asList(values.split(",")));
        filter.setValues(filterValues);

        return filter;
    }
}
