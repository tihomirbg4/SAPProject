package Controllers;

import Models.User;
import Views.SellerView;

import java.text.ParseException;

public class SellerPanelController {
    private ProductController productController;
    private UserController userController;
    public SellerPanelController(UserSession userSession)
    {
         productController = new ProductController(userSession);
         userController = new UserController();
    }

    public void chooseSellerOptions() throws ParseException {
        SellerView sellerView = new SellerView();
        sellerView.printSellerMenu();
        int userChoice = sellerView.getUserChoice();

        while (userChoice != 9) {
            switch (userChoice) {
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
                    productController.sellProduct();
                    break;
                case 5:
                    productController.readAllData();
                    break;
                case 6:
                    productController.viewSoldProducts();
                    break;
                case 7:
                    productController.readSoldProductsSelectedTimePeriod();
                    break;
                case 8:
                    LoginController loginController = new LoginController();
                    User user = loginController.login();
                    RoleController roleController = new RoleController(user);
                    roleController.renderView();
                    break;

            }
            sellerView.printSellerMenu();
            userChoice = sellerView.getUserChoice();
        }
    }
}
