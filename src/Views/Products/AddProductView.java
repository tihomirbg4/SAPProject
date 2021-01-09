package Views.Products;

import Views.View;

import java.util.Scanner;

public class AddProductView extends View {

    public String getProductName()
    {
        System.out.println("Enter product name");
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
        System.out.println("Enter product's price");
        double productPrice = Double.parseDouble(scanner.nextLine());

        return productPrice;
    }

    public void printSuccessfulAddedProduct()
    {
        System.out.println("Successful added product");
    }

    public void printUnsuccessfulAddedProduct()
    {
        System.out.println("Unsuccessful added product");
    }
}
