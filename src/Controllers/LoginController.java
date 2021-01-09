package Controllers;

import DBOperations.UserOperations;
import Models.User;
import Views.LoginView;

public class LoginController {


    public User login()
    {
        LoginView loginView = new LoginView();
        User loggedUser = null;
        while(loggedUser == null)
        {
             String username = loginView.getUsername();
             String password = loginView.getPassword();
             loggedUser = UserOperations.loginUser(username, password);
             if(loggedUser == null)
             loginView.printUnsuccessfulLogin();
        }
        loginView.printSuccessfulLogin(loggedUser);

        return loggedUser;
    }
}
