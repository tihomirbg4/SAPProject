package Views.Products;

import Views.View;

public class SellProductView extends View {

    public int getProductID()
    {
        System.out.println("Enter product ID");
        int productID = Integer.parseInt(scanner.nextLine());

        return productID;
    }

    public int getSoldProductQuantity()
    {
        System.out.println("Enter how much you want to sell");
        int soldProductQuantity = Integer.parseInt(scanner.nextLine());

        return soldProductQuantity;
    }

    public void printSuccessfulSoldProduct()
    {
        System.out.println("Successful sold product");
    }

    public void printUnsuccessfulSoldProduct()
    {
        System.out.println("Unsuccessful sold poduct");
    }
}
