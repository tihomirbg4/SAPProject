package Views.Users;

import Views.View;

public class DeleteUserView extends View {

    public int getUserID()
    {
        System.out.println("Enter user ID you want to delete");
        int userID = Integer.parseInt(scanner.nextLine());

        return userID;
    }

    public void printSuccessfulDeletedUser()
    {
        System.out.println("Successful deleted user");
    }

    public void printUnsuccessfulDeletedUse()
    {
        System.out.println("User not found");
    }
}
