package Views.Products;

import Views.View;

public class ProductQuantityView extends View {
    public final int lessAvailability = 3;

    public void printNotEnoughProductQuantity(String productName)
    {
        System.out.printf("The availability of %s is less than %d%n", productName, lessAvailability);
    }
}
