package com.synisys.xml.common;

import java.util.List;

/**
 * @author Tatevik.Marikyan
 * @since 01-Apr-16.
 */
public class Household {

    private String description;

    private PersonData applicant;

    private List<PersonData> householdMembers;

    public Household(String description, PersonData applicant, List<PersonData> householdMembers) {
        this.description = description;
        this.applicant = applicant;
        this.householdMembers = householdMembers;
    }

    public String getDescription() {
        return description;
    }

    public PersonData getApplicant() {
        return applicant;
    }

    public List<PersonData> getHouseholdMembers() {
        return householdMembers;
    }

    @Override
    public String toString() {
        String householdMembers = "";
        for (PersonData personData: this.getHouseholdMembers()){
            householdMembers += personData.toString() + "\n";
        }

        return "Household{" +
                "description='" + this.description + '\'' +
                ", applicant=" + this.applicant.toString() + "\n" +
                ", householdMembers=" + householdMembers.toString() +
                '}';
    }
}
