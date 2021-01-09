package Models;

public class Seller {
    public static final int SECONDS_IN_A_DAY = 86400;
    private String name;
    private String password;
    private int sellerID;

    public Seller(String name, String password, int sellerID) {
        this.name = name;
        this.password = password;
        this.sellerID = sellerID;
    }

    public int getSellerID() {
        return this.sellerID;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword()
    {
        return this.password;
    }
}
