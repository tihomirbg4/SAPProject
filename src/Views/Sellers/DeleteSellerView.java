package Views.Sellers;

import Views.View;

public class DeleteSellerView extends View {

    public Integer getSellerID()
    {
        System.out.println("Enter seller ID you want to delete");
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

    public void printSuccessfulDeletedSeller()
    {
        System.out.println("Successful deleted seller");
    }

    public void printUnsuccessfulDeletedSeller()
    {
        System.out.println("Seller not found");
    }


}
