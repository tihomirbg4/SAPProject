package Models;

import DBOperations.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    private int productID;
    private String productName;
    private int quantity;
    private String productCategory;
    private double productPricePerKg;

    public Product(int productID, String productName, int quantity, String productCategory, double productPricePerKg) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.productCategory = productCategory;
        this.productPricePerKg = productPricePerKg;
    }

    public int getProductID() {
        return this.productID;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return this.quantity;
    }

    public void setProductQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductCategory() {
        return this.productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public double getProductPricePerKg() {
        return this.productPricePerKg;
    }

    public void setProductPricePerKg(double productPricePerKg) {
        this.productPricePerKg = productPricePerKg;
    }

    public boolean isFoundByID(int productID) {
        boolean isFound = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Products WHERE productID= '" + productID + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Product is found!");
                isFound = true;
            } else {
                System.out.println("Product not found!");
            }


        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isFound;
    }
}
