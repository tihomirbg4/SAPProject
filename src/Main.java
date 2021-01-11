import Controllers.LoginController;
import Controllers.RoleController;
import Models.User;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        LoginController loginController = new LoginController();
        User user = loginController.login();
        RoleController roleController = new RoleController(user);
        roleController.renderView();

    }
}
