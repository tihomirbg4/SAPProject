package Views.Products;

import Views.View;

public class EditProductView extends View {

    public int getProductID()
    {
        System.out.println("Enter item's ID you want to edit");
        int productID = Integer.parseInt(scanner.nextLine());

        return productID;
    }

    public String getNewProductName()
    {
        System.out.println("Enter new product name");
        String productName = scanner.nextLine();

        return productName;
    }

    public int getProductStock()
    {
        System.out.println("Enter product's stock");
        int productStock = Integer.parseInt(scanner.nextLine());

        return productStock;
    }

    public String getProductCategory()
    {
        System.out.println("Enter product category");
        String productCategory = scanner.nextLine();

        return productCategory;
    }

    public double getProductPrice()
    {
        System.out.println("Enter product price");
        double productPrice = Double.parseDouble(scanner.nextLine());

        return productPrice;
    }

    public void printSuccessfulEditProduct()
    {
        System.out.println("Successful edited product");
    }

    public void printUnsuccessfulEditProduct()
    {
        System.out.println("Unsuccessful edited product");
    }
}
