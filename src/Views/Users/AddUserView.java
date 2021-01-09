package Views.Users;

import Views.View;

public class AddUserView extends View {

    public String getUsername()
    {
        System.out.println("Enter username");
        String username = scanner.nextLine();

        return username;
    }

    public String getPassword()
    {
        System.out.println("Enter password");
        String password = scanner.nextLine();

        return password;
    }

    public void printSuccessfulAddedUser()
    {
        System.out.println("Successful added user");
    }

    public void printUnsuccessfulAddedUser()
    {
        System.out.println("User not found");
    }
}
