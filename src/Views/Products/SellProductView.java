package Views.Products;

import Views.View;

public class SellProductView extends View {

    public Integer getProductID()
    {
        System.out.println("Enter product ID you want to sell");
        Integer sellerID = null;
        try {
            sellerID = Integer.parseInt(scanner.nextLine());
            if(sellerID < 0)
            {
                sellerID = null;
            }
        }
        catch(NumberFormatException e)
        {

        }
        return sellerID;
    }

    public Integer getSoldProductQuantity()
    {
        System.out.println("Enter how much you want to sell");
        Integer soldProductQuantity = null;
        try {
            soldProductQuantity = Integer.parseInt(scanner.nextLine());
            if(soldProductQuantity < 0)
            {
                soldProductQuantity = null;
            }
        }
        catch(NumberFormatException e)
        {

        }

        return soldProductQuantity;
    }

    public void printSuccessfulSoldProduct(int soldQuantity)
    {
        System.out.printf("You sold %d from this product%n", soldQuantity);
    }

    public void printUnsuccessfulSoldProduct()
    {
        System.out.println("Product not found");
    }

    public void printNotEnoughQuantity(int quantity)
    {
        System.out.printf("Available product quantity is %d%n", quantity);
    }

    public void printInvalidQuantityMessage()
    {
        System.out.println("Invalid quantity");
    }

    public void printInvalidProductID()
    {
        System.out.println("Invalid product ID");
    }

    public void printSuccessfulSoldProduct()
    {
        System.out.println("Successful sold product");
    }

}
