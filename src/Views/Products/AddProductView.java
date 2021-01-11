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

    public Integer getProductStock()
    {
        System.out.println("Enter product's stock");
        Integer productStock = null;
        try {
            productStock = Integer.parseInt(scanner.nextLine());
            if(productStock < 0)
            {
                productStock = null;
            }
        }
        catch(NumberFormatException e)
        {

        }
        return productStock;
    }

    public String getProductCategory()
    {
        System.out.println("Enter product category");
        String productCategory = scanner.nextLine();

        return productCategory;
    }

    public Double getProductPrice()
    {
        System.out.println("Enter product's price");
        Double productPrice = null;
        try {
            productPrice = Double.parseDouble(scanner.nextLine());
            if(productPrice < 0)
            {
                productPrice = null;
            }
        }
        catch(NumberFormatException e) {

        }

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
