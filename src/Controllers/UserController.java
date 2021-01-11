package Controllers;

import DBOperations.SellerOperations;
import DBOperations.UserOperations;
import Models.User;
import Views.Users.AddUserView;
import Views.Users.DeleteUserView;
import Views.Users.EditUserView;
import Views.Validations.ProductValidationView;

public class UserController {
    ProductValidationView productInputValidation = new ProductValidationView();
    public User addUser()
    {
        AddUserView addUserView = new AddUserView();
        User user = null;

        while(user == null)
        {
            String username = addUserView.getUsername();
            String password = addUserView.getPassword();
            user = new User(username, password, UserOperations.getAutoIncrementValue());
            boolean isSuccessful = UserOperations.addUser(user);
            if(isSuccessful)
                addUserView.printSuccessfulAddedUser();
            else
                addUserView.printUnsuccessfulAddedUser();
        }

        return user;
    }

    public User updateUser()
    {
        EditUserView editUserView = new EditUserView();
        User user = null;

        while(user == null)
        {
            Integer userID = editUserView.getUserID();
            if(userID != null)
            {
                if(UserOperations.findByID(userID) != null)
                {
                    String username = editUserView.getUsername();
                    String password = editUserView.getPassword();
                    user = new User(username, password, userID);
                    boolean isSuccessful = UserOperations.updateUser(user);
                    if(isSuccessful)
                        editUserView.printSuccessfulEditedUser();
                    else
                        editUserView.printUnsuccessfulEditedUser();
                }
                else
                {
                    editUserView.printNotFoundID();
                }
            }
            else
            {
                productInputValidation.printEnterValidUserID();
            }
        }

        return user;
    }

    public  void deleteUser()
    {
        DeleteUserView deleteUserView = new DeleteUserView();
        User user = null;

        while(user == null) {
            Integer userID = deleteUserView.getUserID();
            while (userID == null || UserOperations.findByID(userID) == null) {
                if (userID == null) {
                    productInputValidation.printEnterValidUserID();
                } else {
                    deleteUserView.printUnsuccessfulDeletedUse();
                }

                userID = deleteUserView.getUserID();
            }

            user = UserOperations.findByID(userID);
            if(user != null) {
                boolean isSuccessful = UserOperations.deleteUser(user);
                if(isSuccessful) {
                    deleteUserView.printSuccessfulDeletedUser();
                }
                else {
                    deleteUserView.printUnsuccessfulDeletedUse();
                }
            }
        }
    }

}
