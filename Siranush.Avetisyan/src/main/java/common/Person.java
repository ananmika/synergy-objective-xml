package common;

import common.Address;

/**
 * @author : Siranush.Avetisyan
 * @since : 27/03/2016
 */
public class Person {

    private Integer id;
    private String fullName;
    private Address address;

    public Person(Integer id) {
        this.id = id;
        this.address = new Address();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
