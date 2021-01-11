package Views.Users;

import Views.View;

public class DeleteUserView extends View {

    public Integer getUserID()
    {
        System.out.println("Enter ID you want to delete");
        Integer userID = null;
        try {
            userID = Integer.parseInt(scanner.nextLine());
            if(userID < 0)
            {
                userID = null;
            }
        }
        catch(NumberFormatException e)
        {

        }
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
