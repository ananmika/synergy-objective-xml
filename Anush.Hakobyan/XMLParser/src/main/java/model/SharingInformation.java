package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by anush.hakobyan on 3/29/2016.
 */
public class SharingInformation {

    private boolean isPublicShared;
    private Participant owner;
    private List<Participant> sharedWithParticipants = new ArrayList<>();

    public SharingInformation() {}

    public SharingInformation(boolean isPublicShared, Participant owner, List<Participant> participants) {
        this.isPublicShared = isPublicShared;
        this.owner = owner;
        this.sharedWithParticipants = participants;
    }

    public boolean isPublicShared() {
        return isPublicShared;
    }

    public void setPublicShared(boolean publicShared) {
        isPublicShared = publicShared;
    }

    public Participant getOwner() {
        return owner;
    }

    public void setOwner(Participant owner) {
        this.owner = owner;
    }

    public List<Participant> getSharedWithParticipants() {
        return sharedWithParticipants;
    }

    public void setSharedWithParticipants(List<Participant> sharedWithParticipants) {
        this.sharedWithParticipants = sharedWithParticipants;
    }

    public boolean equals(Object object) {
        if(object == null){
            return false;
        }

        if(!(object instanceof SharingInformation)) {
            return false;
        }

        SharingInformation sharingInfo = (SharingInformation) object;

        return Objects.equals(this.isPublicShared, sharingInfo.isPublicShared)
                && Objects.equals(this.owner, sharingInfo.owner)
                && Objects.equals(this.sharedWithParticipants, sharingInfo.sharedWithParticipants);
    }

    @Override
    public String toString() {
        String resultString = "";
        resultString += "isPublicShared = " + Boolean.toString(this.isPublicShared);
        resultString += ", owner = " + Objects.toString(this.owner);
        resultString += ", participants = ";
        for(Participant participant : this.sharedWithParticipants){
            resultString += "participant =" + Objects.toString(participant) + ",";
        }
        return resultString;
    }
}
