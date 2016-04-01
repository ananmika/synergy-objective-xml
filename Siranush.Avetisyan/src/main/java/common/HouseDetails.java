package common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Siranush.Avetisyan
 * @since : 27/03/2016
 */
public class HouseDetails {

    private String code;
    private Address address;
    private Integer roomsCount;
    private BigDecimal livingSpace;
    private BigDecimal price;
    private List<String> activeSellersNameList;

    public HouseDetails() {
        this.address = new Address();
        this.activeSellersNameList = new ArrayList<>();
    }

    public HouseDetails(String code) {
        this.code = code;
        this.address = new Address();
        this.activeSellersNameList = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(Integer roomsCount) {
        this.roomsCount = roomsCount;
    }

    public BigDecimal getLivingSpace() {
        return livingSpace;
    }

    public void setLivingSpace(BigDecimal livingSpace) {
        this.livingSpace = livingSpace;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getActiveSellersNameList() {
        return activeSellersNameList;
    }

    public void setActiveSellersNameList(List<String> activeSellersNameList) {
        this.activeSellersNameList = activeSellersNameList;
    }

    @Override
    public String toString() {
        String stringForPrint = "Active seller" + (this.activeSellersNameList.size() > 1 ? "s " : " ") + "for " + this.code + " house " + (this.activeSellersNameList.size() > 1 ? "are " : "is ");
        for(String sellerName : this.activeSellersNameList) {
            stringForPrint+= sellerName + ", ";
        }
        return stringForPrint.substring(0, stringForPrint.length() - 2);
    }
}
