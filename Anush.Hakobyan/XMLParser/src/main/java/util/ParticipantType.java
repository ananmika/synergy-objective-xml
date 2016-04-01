package util;

/**
 * Created by anush.hakobyan on 3/29/2016.
 */
public enum ParticipantType {
    USER("USER"),
    GROUP("GROUP"),
    ROLE("ROLE");

    private String id;

    private ParticipantType(String id) {
        this.id = id;
    }

    public static ParticipantType getUserType(String id){
        for (ParticipantType type : ParticipantType.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("ParticipantType with corrent id: %s not found", id));
    }
}
