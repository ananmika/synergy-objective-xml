package STAXParser;

public class Item {
    private String Name;
    private Integer price = Integer.valueOf(0);
    private Integer quantity;


    public Item(String name) {
        Name = name;
    }

    public Item() {
    }

    public Integer getTotalAmount(){
        if(price != null && quantity!= null) {
            return new Integer(price * quantity);
        }
        return Integer.valueOf(0);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
