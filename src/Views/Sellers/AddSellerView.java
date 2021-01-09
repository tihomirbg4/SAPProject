package Views.Sellers;

import Views.View;

public class AddSellerView extends View {
    public String getUsername()
    {
        System.out.println("Enter seller name");
        String sellerName = scanner.nextLine();

        return sellerName;
    }

    public String getSellerPassword()
    {
        System.out.println("Enter seller password");
        String sellerPassword = scanner.nextLine();

        return sellerPassword;
    }

    public void printSuccessfulAddedSeller()
    {
        System.out.println("Successful added seller");
    }
    public void printUnsuccessfulAddedSeller()
    {
        System.out.println("Seller not found");
    }

}
