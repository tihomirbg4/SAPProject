package DBOperations;

import Models.Product;
import Models.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class ProductOperations {
    public static boolean addProduct(Product product) {
        boolean isSuccessful = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps;
        try {
            String sql = "INSERT INTO Products(productID, productName, Stock, productCategory, productPrice) VALUES(? ,?, ?, ?, ?) ";
            ps = con.prepareStatement(sql);
            ps.setString(2, product.getProductName());
            ps.setString(3, String.valueOf(product.getProductQuantity()));
            ps.setString(4, product.getProductCategory());
            ps.setString(5, String.valueOf(product.getProductPricePerKg()));
            isSuccessful = ps.executeUpdate() == 1;


        } catch (SQLException e) {
           e.printStackTrace();
        }

        return isSuccessful;

    }

    public static int getAutoIncrementValue()
    {
        int autoIncrementValue = 0;
        Connection connection = DbConnection.connect();
        PreparedStatement ps;
        try {
            String sql = "SELECT seq FROM sqlite_sequence WHERE name = 'Products'";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                 autoIncrementValue = rs.getInt(1);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autoIncrementValue;
    }

    public static boolean updateProduct(int productID, Product newProduct) {
        Connection con = DbConnection.connect();
        PreparedStatement ps;
        try {
            String sql = "UPDATE Products SET productName = ?, Stock = ?, productCategory = ?, productPrice = ? WHERE productID= '" + productID + "'";
            ps = con.prepareStatement(sql);
            ps.setString(1, newProduct.getProductName());
            ps.setInt(2, newProduct.getProductQuantity());
            ps.setString(3, newProduct.getProductCategory());
            ps.setDouble(4, newProduct.getProductPricePerKg());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public static Product findByID(int productID)  {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            String sql = "SELECT * FROM Products WHERE productID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getDouble(5));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static boolean deleteProduct(Product product) {
        boolean isSuccessful = false;
        if(product == null)
        {
            return isSuccessful;
        }
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE from Products WHERE productID = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, product.getProductID());
            isSuccessful = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ps != null;
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSuccessful;
    }

//    public static boolean readAllData() {
//        boolean isSuccessful = false;
//        Connection con = DbConnection.connect();
//        PreparedStatement ps = null;
//
//        try {
//            String sql = "SELECT * from Products";
//            ps = con.prepareStatement(sql);
//            isSuccessful = ps.executeUpdate() == 1;
//        } catch (SQLException e) {
//           e.printStackTrace();
//        } finally {
//            try {
//               ps.close();
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return isSuccessful;
//    }

    public static boolean readAllData() {
        boolean isSuccessful = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * from Products";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                int stock = Integer.parseInt(rs.getString("Stock"));
                String category = rs.getString("productCategory");
                double productPrice = Double.parseDouble(rs.getString("productPrice"));
                isSuccessful = true;

                System.out.printf("Item %s has %d pieces in stock, category: %s and cost %.2f per kg.%n",  productName, stock, category, productPrice);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
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
                System.out.println(e.toString());
            }
        }

        return isSuccessful;
    }

    public static boolean sellProduct(Product product, int quantitySold, Seller seller) {
        boolean isSuccessful = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        String sql1 = "INSERT INTO SoldProducts(productID, quantitySold, orderCompletedOn, sellerID) VALUES(?, ?, ?, ?)";
        try {
            String sql = "UPDATE Products SET Stock = ? WHERE productID = ?";
            if (product.getProductQuantity() >= quantitySold) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, product.getProductQuantity() - quantitySold);
                ps.setInt(2, product.getProductID());
                ps1 = con.prepareStatement(sql1);
                ps1.setInt(1, product.getProductID());
                ps1.setInt(2, quantitySold);
                ps1.setLong(3, Instant.now().getEpochSecond());
                ps1.setInt(4, seller.getSellerID());
                ps1.execute();
                isSuccessful = ps.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }
}
