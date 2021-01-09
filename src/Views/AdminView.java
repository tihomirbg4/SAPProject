package Views;

import java.util.Scanner;

public class AdminView extends View {

    public int getUserChoice()
    {
        System.out.println("Enter option");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printAdminMenu()
    {
        System.out.println("1. Add product");
        System.out.println("2. Edit product");
        System.out.println("3. Delete product");
        System.out.println("4. Add seller");
        System.out.println("5. Edit seller");
        System.out.println("6. Delete seller");
        System.out.println("7. Products overview");
        System.out.println("8. Sellers overview");
        System.out.println("9. Logout");
        System.out.println("10. Exit");
    }
}
