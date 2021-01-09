package Views.Sellers;

import Views.View;
import org.w3c.dom.ls.LSOutput;

public class EditSellerView extends View {

    public int getSellerID()
    {
        System.out.println("Enter seller ID you want to edit");
        int sellerID = Integer.parseInt(scanner.nextLine());

        return sellerID;
    }

    public String getSellerUsername()
    {
        System.out.println("Enter new seller username");
        String sellerUsername = scanner.nextLine();

        return sellerUsername;
    }

    public String getSellerPassword()
    {
        System.out.println("Enter new seller password");
        String sellerPassword = scanner.nextLine();

        return sellerPassword;
    }

    public void printSuccessfulEditedSeller()
    {
        System.out.println("Successful edited seller");
    }

    public void printUnsuccessfulEditedSeller()
    {
        System.out.println("Unsuccessful edited seller");
    }

    public void printNotFoundID()
    {
        System.out.println("ID not found");
    }
}
