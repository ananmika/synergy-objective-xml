package model;

import util.ParticipantType;

import java.util.Objects;

/**
 * Created by anush.hakobyan on 3/29/2016.
 */
public class Participant {
    private Integer id;
    private ParticipantType type;
    private String name;

    public Participant(Integer id) {
        this.id = id;
    }

    public Participant(Integer id, ParticipantType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public ParticipantType getType() {
        return type;
    }

    public void setType(ParticipantType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {

        if(object == null) {
            return false;
        }

        if(!(object instanceof Participant)) {
            return false;
        }

        Participant participant = (Participant) object;
        return Objects.equals(this.id, participant.id)
                && Objects.equals(this.type, participant.type)
                && Objects.equals(this.name, participant.name);
    }

    @Override
    public String toString() {
        String resultString = "";
        resultString += "id = " + Objects.toString(this.id);
        resultString += ", name = " + this.name;
        resultString += ", type = " + Objects.toString(this.type);

        return resultString;
    }
}
