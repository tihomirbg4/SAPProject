package Views.Products;

import Views.View;

public class DeleteProductView extends View {

    public Integer getProductID()
    {
        System.out.println("Enter product ID you want to delete");
        Integer productID = null;
        try {
            productID = Integer.parseInt(scanner.nextLine());
            if(productID < 0)
            {
                productID = null;
            }
        }
        catch(NumberFormatException e)
        {

        }
        return productID;
    }

    public void printSuccessfulDeletedProduct()
    {
        System.out.println("Successful deleted product");
    }

    public void printUnsuccessfulDeletedProduct()
    {
        System.out.println("Product not found");
    }

    public void printProductNotFound()
    {
        System.out.println("Product not found");
    }
}
