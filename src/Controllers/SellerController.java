package Controllers;

import DBOperations.ProductOperations;
import DBOperations.SellerOperations;
import Models.Product;
import Models.Seller;
import Views.Sellers.*;

public class SellerController {
    public Seller addSeller()
    {
        AddSellerView addSellerView = new AddSellerView();
        Seller seller = null;

        while(seller == null)
        {
            String sellerUsername = addSellerView.getUsername();
            String sellerPassword = addSellerView.getSellerPassword();
            seller = new Seller(sellerUsername, sellerPassword, SellerOperations.getAutoIncrementValue());
            boolean isSuccessful = SellerOperations.addSeller(seller);
            if(isSuccessful)
                addSellerView.printSuccessfulAddedSeller();
            else
                addSellerView.printUnsuccessfulAddedSeller();

        }

        return seller;
    }

    public Seller editSeller()
    {
        EditSellerView editSellerView = new EditSellerView();
        Seller seller = null;

        while(seller == null)
        {
            int sellerID = editSellerView.getSellerID();
            if(SellerOperations.findByID(sellerID) != null)
            {
                String sellerUsername = editSellerView.getSellerUsername();
                String sellerPassword = editSellerView.getSellerPassword();
                seller = new Seller(sellerUsername, sellerPassword, sellerID);
                boolean isSuccessful = SellerOperations.updateSeller(seller);
                if(isSuccessful)
                    editSellerView.printSuccessfulEditedSeller();
                else
                    editSellerView.printUnsuccessfulEditedSeller();
            }
            else
            {
                editSellerView.printNotFoundID();
            }
        }

        return seller;
    }

    public void deleteSeller()
    {
        DeleteSellerView deleteSellerView = new DeleteSellerView();
        Seller seller = null;
        while(seller == null)
        {
            int sellerID = deleteSellerView.getSellerID();
            seller = SellerOperations.findByID(sellerID);

            if(SellerOperations.deleteSeller(seller))
            {
                deleteSellerView.printSuccessfulDeletedSeller();
            }
            else
            {
                deleteSellerView.printUnsuccessfulDeletedSeller();
            }
        }
    }

    public void readSellers() {
        SellersOverview sellersOverview = new SellersOverview();
        sellersOverview.printListMessage();
        SellerOperations.readSellers();
    }

}
