package Controllers;

import Models.Product;
import Models.Seller;
import Views.AdminView;

import java.sql.SQLException;

public class AdminPanelController {
    public void printOptions() {
        AdminView adminView = new AdminView();
        adminView.printAdminMenu();
        int choice = adminView.getUserChoice();
        ProductController productController = new ProductController();
        SellerController sellerController = new SellerController();
        switch(choice)
        {
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


        }
    }
}
