package Views.Users;

import Views.View;

public class EditUserView extends View {
    public Integer getUserID()
    {
        System.out.println("Enter user ID you want to edit");
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

    public String getUsername()
    {
        System.out.println("Enter new username");
        String username = scanner.nextLine();

        return username;
    }

    public String getPassword()
    {
        System.out.println("Enter new password");
        String password = scanner.nextLine();

        return password;
    }

    public void printSuccessfulEditedUser()
    {
        System.out.println("Successful edited user");
    }

    public void printUnsuccessfulEditedUser()
    {
        System.out.println("Unsuccessful edited product");
    }

    public void printNotFoundID()
    {
        System.out.println("User ID not found");
    }
}
