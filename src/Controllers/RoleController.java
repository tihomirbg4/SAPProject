package Controllers;

import Models.User;
import Roles.Role;
import Roles.RoleManager;

import java.text.ParseException;

public class RoleController {
    private UserSession userSession;

    public RoleController(User user) {
        userSession = new UserSession(user);
    }

    public void renderView() throws ParseException {
        RoleManager roleManager = new RoleManager();
            if(roleManager.getRole(userSession.getUser()) == Role.ADMIN)
            {
                AdminPanelController adminPanelController = new AdminPanelController(userSession);
                adminPanelController.chooseAdminOptions();
            }
            else if(roleManager.getRole(userSession.getUser()) == Role.SELLER)
            {
                SellerPanelController sellerPanelController = new SellerPanelController(userSession);
                sellerPanelController.chooseSellerOptions();
            }
            else if(roleManager.getRole(userSession.getUser()) == Role.USER)
            {
                RegularUserController regularUserController = new RegularUserController();
                regularUserController.printNotAllowedUser();
                LoginController loginController = new LoginController();
                User user = loginController.login();
                RoleController roleController = new RoleController(user);
                roleController.renderView();
            }
    }
}
