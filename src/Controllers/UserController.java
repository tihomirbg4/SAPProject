package Controllers;

import DBOperations.SellerOperations;
import DBOperations.UserOperations;
import Models.Seller;
import Models.User;
import Views.Sellers.DeleteSellerView;
import Views.Users.AddUserView;
import Views.Users.DeleteUserView;
import Views.Users.EditUserView;

public class UserController {
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
            int userID = editUserView.getUserID();
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

        return user;
    }

    public  void deleteUser()
    {
        DeleteUserView deleteUserView = new DeleteUserView();
        User user = null;

        while(user == null)
        {
            int userID = deleteUserView.getUserID();
            user = UserOperations.findByID(userID);

            if(UserOperations.deleteUser(user))
                deleteUserView.printSuccessfulDeletedUser();
            else
                deleteUserView.printUnsuccessfulDeletedUse();
        }
    }
}
