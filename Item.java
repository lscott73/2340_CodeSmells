public abstract class Item {
    private String name;
    private double price;
    private int quantity;
    private DiscountType discount;

    public Item(String name, double price, int quantity, DiscountType discount) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public DiscountType getDiscount() {
        return discount;
    }

    public abstract double calculatePriceAfterDiscount();
}
