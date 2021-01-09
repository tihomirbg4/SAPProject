package Views.Products;

import Views.View;

public class DeleteProductView extends View {

    public int getProductID()
    {
        System.out.println("Enter item's ID you want to delete");
        int productID = Integer.parseInt(scanner.nextLine());

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
}
