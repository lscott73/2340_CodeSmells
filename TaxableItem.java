public class TaxableItem extends Item {
    private double taxRate;

    public TaxableItem(String name, double price, int quantity, DiscountType discountType, double discountAmount, double taxRate) {
        super(name, price, quantity, discountType, discountAmount);
        setTaxRate(taxRate);
    }

    public double getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(double rate) {
        if(rate>=0){
            taxRate = rate;
        }
    }
}

