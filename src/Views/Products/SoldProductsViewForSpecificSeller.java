package Views.Products;

import Views.View;
import com.sun.security.jgss.GSSUtil;

public class SoldProductsViewForSpecificSeller extends View {

    public Integer getSellerID()
    {
        System.out.println("Enter seller ID you want to edit");
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


    public void noProductsSoldYet()
    {
        System.out.println("This seller hasn't sold anything yet ");
    }

    public void noSellerFound()
    {
        System.out.println("Seller not found");
    }

    public void invalidProductIDMessage()
    {
        System.out.println("Invalid product ID");
    }
}
