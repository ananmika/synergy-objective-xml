package com.synisys.xml.common;

/**
 * Created by Tatevik
 * since  01-Apr-16.
 */
public class PersonData {

    private String firstName;

    private String lastName;

    private String nib;

    private Integer age;

    private String gender;

    public PersonData() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNib() {
        return nib;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "PersonData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nib='" + nib + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                "}\n";
    }
}
