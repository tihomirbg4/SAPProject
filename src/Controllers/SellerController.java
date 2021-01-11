package Controllers;

import DBOperations.ProductOperations;
import DBOperations.SellerOperations;
import Models.User;
import Views.Sellers.*;
import Views.Validations.ProductValidationView;

public class SellerController {
    ProductValidationView productInputValidation = new ProductValidationView();
    public void addSeller()
    {
        AddSellerView addSellerView = new AddSellerView();
        User seller = null;

        while(seller == null)
        {
            String sellerUsername = addSellerView.getUsername();
            String sellerPassword = addSellerView.getSellerPassword();
            seller = new User(sellerUsername, sellerPassword, SellerOperations.getAutoIncrementValue());
            boolean isSuccessful = SellerOperations.addSeller(seller);
            if(isSuccessful)
                addSellerView.printSuccessfulAddedSeller();
            else
                addSellerView.printUnsuccessfulAddedSeller();

        }

    }

    public void editSeller() {
        EditSellerView editSellerView = new EditSellerView();
        User seller = null;

        while (seller == null) {
            Integer sellerID = editSellerView.getSellerID();
            while (sellerID == null || SellerOperations.findByID(sellerID) == null) {
                if (sellerID != null) {
                    if (SellerOperations.findByID(sellerID) != null) {
                        String sellerUsername = editSellerView.getSellerUsername();
                        String sellerPassword = editSellerView.getSellerPassword();
                        seller = new User(sellerUsername, sellerPassword, sellerID);
                        boolean isSuccessful = SellerOperations.updateSeller(seller);
                        if (isSuccessful)
                            editSellerView.printSuccessfulEditedSeller();
                        else
                            editSellerView.printUnsuccessfulEditedSeller();
                    } else {
                        editSellerView.printSellerNotFound();
                    }
                } else {
                    productInputValidation.printEnterValidSellerID();
                }

                sellerID = editSellerView.getSellerID();
            }
            if(SellerOperations.findByID(sellerID) == null) {
                editSellerView.printSellerNotFound();
            }
            else {
                if (SellerOperations.findByID(sellerID) != null) {
                    String sellerUsername = editSellerView.getSellerUsername();
                    String sellerPassword = editSellerView.getSellerPassword();
                    seller = new User(sellerUsername, sellerPassword, sellerID);
                    boolean isSuccessful = SellerOperations.updateSeller(seller);
                    if (isSuccessful)
                        editSellerView.printSuccessfulEditedSeller();
                    else
                        editSellerView.printUnsuccessfulEditedSeller();
                } else {
                    editSellerView.printSellerNotFound();
                }
            }

        }
    }

    public void deleteSeller()
    {
        DeleteSellerView deleteSellerView = new DeleteSellerView();
        User seller = null;
        while(seller == null) {
            Integer sellerID = deleteSellerView.getSellerID();
            while (sellerID == null || SellerOperations.findByID(sellerID) == null) {
                if (sellerID == null) {
                    productInputValidation.printEnterValidSellerID();
                } else {
                    deleteSellerView.printUnsuccessfulDeletedSeller();
                }

                sellerID = deleteSellerView.getSellerID();
            }

            seller = SellerOperations.findByID(sellerID);
            if(seller != null) {
                boolean isSuccessful = SellerOperations.deleteSeller(seller);
                if(isSuccessful) {
                    deleteSellerView.printSuccessfulDeletedSeller();
                }
                else {
                    deleteSellerView.printUnsuccessfulDeletedSeller();
                }
            }
        }
    }

    public void readSellers() {
        SellersOverview sellersOverview = new SellersOverview();
        sellersOverview.printListMessage();
        SellerOperations.readSellers();
    }


}
