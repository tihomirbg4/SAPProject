package Views;

public class SellerView extends View {

    public int getUserChoice()
    {
        System.out.println("Enter option");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printSellerMenu() {
        System.out.println("1. Add user");
        System.out.println("2. Edit user");
        System.out.println("3. Delete user");
        System.out.println("4. Sell product");
        System.out.println("5. Available products overview");
        System.out.println("6. Sold products overview");
        System.out.println("7. Sold products for specific seller");
        System.out.println("8. Users overview");
        System.out.println("9. Logout");
        System.out.println("10. Exit");
    }
}
