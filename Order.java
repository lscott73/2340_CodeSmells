import java.util.List;

public class Order {
    private List<Item> items;
    private String customerName;
    private String customerEmail;
    private boolean hasGiftCard;
    private double giftCard;


    public Order(List<Item> items, String customerName, String customerEmail) {
        this.items = items;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.hasGiftCard = checkForGiftCard(items);
    }

    public boolean hasGiftCard() {
        return hasGiftCard;
    }

    public double calculateTotalPrice() {
    	double total = 0.0;
    	for (Item item : items) {
        	double price = item.getPrice();
        	switch (item.getDiscountType()) {
            	case PERCENTAGE:
                	price -= item.getDiscountAmount() * price;
                	break;
            	case AMOUNT:
                	price -= item.getDiscountAmount();
                	break;
            	default:
                	// no discount
                	break;
        	}
        	total += price * item.getQuantity();
       	    if (item instanceof TaxableItem) {
                TaxableItem taxableItem = (TaxableItem) item;
                double tax = taxableItem.getTaxRate() / 100.0 * item.getPrice();
                total += tax;
            }
        }
        if (total > 100.0) {
        	total *= 0.9; // apply 10% discount for orders over $100
    	}
    	if (hasGiftCard()) {
            giftCard = 10.0;
        	total -= giftCard;
    	}
    	return total;
    }

    public void sendConfirmationEmail() {
        String message = "Thank you for your order, " + customerName + "!\n\n" +
                "Your order details:\n";
        for (Item item : items) {
            message += item.getName() + " - " + item.getPrice() + "\n";
        }
        message += "Total: " + calculateTotalPrice();
        EmailSender.sendEmail(customerEmail, "Order Confirmation", message);
    }


    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    private boolean checkForGiftCard(List<Item> items) {
        for (Item item : items) {
            if (item instanceof GiftCardItem) {
                return true;
            }
        }
        return false;
    }

   public void printOrder() {
        System.out.println("Order Details:");
        for (Item item : items) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }
   }

   public void addItemsFromAnotherOrder(Order otherOrder) {
        for (Item item : otherOrder.getItems()) {
            items.add(item);
        }
   }

}

