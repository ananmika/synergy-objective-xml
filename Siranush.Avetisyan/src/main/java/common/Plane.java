package common;

/**
 * @author : Siranush.Avetisyan
 * @since : 27/03/2016
 */
public class Plane {

    private String model;
    private Integer rating;
    private Integer passengerCount;
    private Address addressFrom;
    private Address addressTo;
    private Person pilot;

    public Plane(Integer rating) {
        this.rating = rating;
        this.addressFrom = new Address();
        this.addressTo = new Address();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public Address getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(Address addressFrom) {
        this.addressFrom = addressFrom;
    }

    public Address getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(Address addressTo) {
        this.addressTo = addressTo;
    }

    public Person getPilot() {
        return pilot;
    }

    public void setPilot(Person pilot) {
        this.pilot = pilot;
    }
}
