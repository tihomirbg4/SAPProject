package Controllers;

import Models.User;
import Views.AdminView;

import java.text.ParseException;

public class AdminPanelController {
    private ProductController productController;
    SellerController sellerController = new SellerController();
    public AdminPanelController(UserSession userSession)
    {
        productController = new ProductController(userSession);

    }
    public void chooseAdminOptions() throws ParseException {
        AdminView adminView = new AdminView();
        productController.lowProductQuantity();
        adminView.printAdminMenu();
        int choice = adminView.getUserChoice();
        while (choice != 11) {
            switch (choice) {
                case 1:
                    productController.addProduct();
                    break;
                case 2:
                    productController.editProduct();
                    break;
                case 3:
                    productController.deleteProduct();
                    break;
                case 4:
                    sellerController.addSeller();
                    break;
                case 5:
                    sellerController.editSeller();
                    break;
                case 6:
                    sellerController.deleteSeller();
                    break;
                case 7:
                    productController.readAllData();
                    break;
                case 8:
                    sellerController.readSellers();
                    break;
                case 9:
                    productController.readSoldProductsForSpecificSeller();
                    break;
                case 10:
                    LoginController loginController = new LoginController();
                    User user = loginController.login();
                    RoleController roleController = new RoleController(user);
                    roleController.renderView();
                    break;


            }
            adminView.printAdminMenu();
            choice = adminView.getUserChoice();
        }
    }
}
