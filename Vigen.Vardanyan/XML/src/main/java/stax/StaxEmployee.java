package main.java.stax;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public class StaxEmployee {
    String id;
    String firstName;
    String lastName;
    String location;

    @Override
    public String toString() {
        return firstName + " " + lastName + "(" + id + ")" + location;
    }
}
