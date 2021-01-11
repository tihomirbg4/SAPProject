package Controllers;

import Views.Users.RegularUserView;

public class RegularUserController {

    public void printNotAllowedUser()
    {
        RegularUserView regularUserView = new RegularUserView();
        regularUserView.printNotAllowedLogin();
    }

}
