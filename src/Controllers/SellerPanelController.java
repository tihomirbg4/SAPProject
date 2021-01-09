package Controllers;

import Models.Seller;
import Views.SellerView;

public class SellerPanelController {
    Seller seller;

    private ProductController productController = new ProductController();
    public void printOptions()
    {
        SellerView sellerView = new SellerView();
        sellerView.printSellerMenu();
        int userChoice = sellerView.getUserChoice();
        UserController userController = new UserController();

        switch(userChoice)
        {
            case 1:
              userController.addUser();
              break;
            case 2:
                userController.updateUser();
                break;
            case 3:
                userController.deleteUser();
                break;
            case 4:
                ProductController productController = new ProductController();

        }
    }
}
