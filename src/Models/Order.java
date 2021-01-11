package Models;

import Controllers.UserSession;

import java.util.Date;

public class Order {
    private User seller;
    private Product product;
    private int id;
    private Date completedOn;


    public Order(Product product, long timestamp, User seller) {
        this.product = product;
        completedOn = new Date(timestamp * 1000);
        this.seller = seller;

    }

    @Override
    public String toString() {
        return String.format("Item %s sold by %s sold on %s with quantity %d category %s cost %.2f lv%n ",
                product.getProductName(), seller.getUsername(), completedOn, product.getProductQuantity(), product.getProductCategory(),
                product.getProductPricePerKg());
    }
}
