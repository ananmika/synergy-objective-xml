package com.synisys.xml.SAXParser;

import java.math.BigDecimal;

/**
 * Created by VaheMarikyan on 4/1/16.
 */
public class Item {

    private String name;

    private String size;

    private Integer quantity;

    private BigDecimal price;

    private String condition;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }


    @Override
    public String toString() {
        return "Item : " +
                "name = '" + this.name + '\'' +
                ", size = '" + this.size + '\'' +
                ", price = '" + this.price + '\'' +
                ", quantity = '" + this.quantity + '\'' +
                ", condition = " + this.condition;
    }
}
