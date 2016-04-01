package com.synisys.xml.common;

import java.util.Date;

/**
 * Created by Tatevik
 * since  01-Apr-16.
 */
public class PersonData {

    private Integer id;

    private String category;

    private String firstName;

    private String lastName;

    private String nib;

    private Integer age;

    private String gender;

    private Date dateOfBirth;

    private String nationality;

    private Boolean isHouseholdHead;

    public PersonData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNib() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Boolean getHouseholdHead() {
        return isHouseholdHead;
    }

    public void setHouseholdHead(Boolean householdHead) {
        isHouseholdHead = householdHead;
    }

    @Override
    public String toString() {
        return "PersonData{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nib='" + nib + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", nationality='" + nationality + '\'' +
                ", isHouseholdHead=" + isHouseholdHead +
                '}';
    }
}
