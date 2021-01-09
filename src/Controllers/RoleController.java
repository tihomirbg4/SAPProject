package Controllers;

import Models.User;
import Roles.Role;
import Roles.RoleManager;

import java.sql.SQLException;

public class RoleController {
    User user;

    public RoleController(User user) {
        this.user = user;
    }

    public void renderView(User user) throws SQLException {
        RoleManager roleManager = new RoleManager();
        if(roleManager.getRole(user) == Role.ADMIN)
        {
           AdminPanelController adminPanelController = new AdminPanelController();
           adminPanelController.printOptions();
        }
        else if(roleManager.getRole(user) == Role.SELLER)
        {
            SellerPanelController sellerPanelController = new SellerPanelController();
            sellerPanelController.printOptions();
        }
    }
}
