package Views.Products;

import Views.View;

public class EditProductView extends View {

    public Integer getProductID()
    {
        System.out.println("Enter product's ID");
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

    public String getNewProductName()
    {
        System.out.println("Enter new product name");
        String productName = scanner.nextLine();

        return productName;
    }

    public Integer getProductQuantity()
    {
        System.out.println("Enter new product quantity");
        Integer productQuantity = null;
        try {
            productQuantity = Integer.parseInt(scanner.nextLine());
            if(productQuantity < 0)
            {
                productQuantity = null;
            }
        }
        catch(NumberFormatException e)
        {

        }
        return productQuantity;
    }

    public String getProductCategory()
    {
        System.out.println("Enter new product category");
        String productCategory = scanner.nextLine();

        return productCategory;
    }

    public Double getNewProductPrice()
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

    public void printSuccessfulEditProduct()
    {
        System.out.println("Successful edited product");
    }

    public void printUnsuccessfulEditProduct()
    {
        System.out.println("Unsuccessful edited product");
    }

    public void printProductNotFound()
    {
        System.out.println("Product not found");
    }

}
