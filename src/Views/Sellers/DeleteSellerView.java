package Views.Sellers;

import Views.View;

public class DeleteSellerView extends View {

    public int getSellerID()
    {
        System.out.println("Enter seller ID you want to delete");
        int sellerID = Integer.parseInt(scanner.nextLine());

        return sellerID;
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
