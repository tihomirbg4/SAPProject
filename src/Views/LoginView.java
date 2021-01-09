package Views;

import Models.User;

import java.util.Scanner;

public class LoginView extends View{

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

    public void printSuccessfulLogin(User user)
    {
        System.out.println("Successful login " + user.getUsername());
    }

    public void printUnsuccessfulLogin()
    {
        System.out.println("Unsuccessful login, try again");
    }
}
