package DBOperations;

import Controllers.ProductController;
import Controllers.UserSession;
import Models.Order;
import Models.Product;
import Models.User;
import Views.Products.ProductQuantityView;
import Views.Products.SellProductView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ProductOperations {
    private UserSession userSession;
    public static final int SECONDS_IN_A_DAY = 86400;
    public ProductOperations(UserSession userSession)
    {
        this.userSession = userSession;
    }


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
            String sql = "UPDATE Products SET productName = ?, Stock = ?, productCategory = ?, productPrice = ? WHERE productID = '" + productID + "'";
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

                System.out.printf("Item %s with ID %d has %d pieces in stock, category: %s and cost %.2f per kg.%n",  productName, productID, stock, category, productPrice);
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

    public static boolean sellProduct(Product product, int quantitySold, User seller) {
        boolean isSuccessful = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        String sql1 = "INSERT INTO SoldProducts(productID, quantitySold, orderCompletedOn, sellerID) VALUES(?, ?, ?, ?)";
        try {
            String sql = "UPDATE Products SET Stock = ? WHERE productID = ?";
            String sql2 = "SELECT Stock FROM Products WHERE productID = ?";
            if (product.getProductQuantity() >= quantitySold) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, product.getProductQuantity() - quantitySold);
                ps.setInt(2, product.getProductID());
                ps1 = con.prepareStatement(sql1);
                ps1.setInt(1, product.getProductID());
                ps1.setInt(2, quantitySold);
                ps1.setLong(3, Instant.now().getEpochSecond());
                ps1.setInt(4, seller.getUserID());
                ps1.execute();
                isSuccessful = ps.executeUpdate() == 1;
            }
            else
            {
                SellProductView sellProductView = new SellProductView();
                ps2 = con.prepareStatement(sql2);
                ps2.setInt(1, product.getProductID());
                rs = ps2.executeQuery();
                while(rs.next()) {
                    int availableQuantity = rs.getInt("Stock");
                    sellProductView.printNotEnoughQuantity(availableQuantity);
                    isSuccessful = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    public static void viewSoldProducts()
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User seller = null;
        try {
            String sql = "SELECT users.username,SoldProducts.productID, SoldProducts.orderCompletedOn, SoldProducts.quantitySold, SoldProducts.sellerID, Products.productName,Products.productCategory,Products.productPrice FROM SoldProducts INNER JOIN Products ON SoldProducts.productID = Products.productID INNER JOIN users ON SoldProducts.sellerID = users.ID";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next())
            {
                String sellerUsername = rs.getString("username");
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                int quantitySold = rs.getInt("quantitySold");
                int sellerID = rs.getInt("sellerID");
                 seller = new User(sellerUsername, sellerID);
                String productCategory = rs.getString("productCategory");
                double productPrice = rs.getDouble("productPrice");
                long orderCompletedOn = rs.getLong("orderCompletedOn");
              Order order = new Order(new Product(productID, productName, quantitySold, productCategory, productPrice)
              , orderCompletedOn, seller);
                System.out.println(order);
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Order> readSoldProductsForSpecificSeller(User seller) {
        Connection con = DbConnection.connect();
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT users.username,SoldProducts.orderID,Products.productID, Products.productName, Products.productPrice, productCategory, SoldProducts.quantitySold, SoldProducts.orderCompletedOn FROM SoldProducts INNER JOIN Products ON SoldProducts.productID = Products.productID INNER JOIN users ON SoldProducts.sellerID = users.ID";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String sellerName = rs.getString("username");
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                int quantitySold = rs.getInt("quantitySold");
                String productCategory = rs.getString("productCategory");
                double productPrice = rs.getDouble("productPrice");
                long orderCompletedOn = rs.getLong("orderCompletedOn");
                Order order = new Order(new Product(productID, productName, quantitySold, productCategory, productPrice), orderCompletedOn, seller);
                System.out.printf("Item %s sold by %s sold on %s with quantity %d category %s cost %.2f lv%n", productName, sellerName, orderCompletedOn, quantitySold, productCategory, productPrice);
                //System.out.println(order);
                orders.add(order);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    public static boolean readSoldProductsSelectedTimePeriod(String startingDate, String finalDate, User seller) throws ParseException {
        boolean isSuccessful= false;
        Connection con = DbConnection.connect();
        long startingDateTimestamp = new SimpleDateFormat("dd.MM.yyyy").parse(startingDate).getTime() / 1000;
        long finalDateTimestamp = new SimpleDateFormat("dd.MM.yyyy").parse(finalDate).getTime() / 1000 + SECONDS_IN_A_DAY;
        try {
            String sql = "SELECT Products.productID, Products.productName, Products.productCategory, Products.productPrice, " +
                    "SoldProducts.quantitySold, SoldProducts.orderCompletedOn from Products INNER JOIN SoldProducts " +
                    "ON Products.productID=SoldProducts.productID WHERE orderCompletedOn >= ? AND orderCompletedOn <= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, startingDateTimestamp);
            ps.setLong(2, finalDateTimestamp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                int quantitySold = rs.getInt("quantitySold");
                String productCategory = rs.getString("productCategory");
                double productPrice = rs.getDouble("productPrice");
                long orderCompletedOn = rs.getLong("orderCompletedOn");
                Order order = new Order(new Product(productID, productName, quantitySold, productCategory, productPrice), orderCompletedOn, seller);
                isSuccessful = true;
                System.out.println(order);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSuccessful;
    }

    public static void proccessLowQuantity() {

        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ProductQuantityView productQuantityView = new ProductQuantityView();
        try {
            String sql = "SELECT * FROM Products WHERE Stock < 3";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                int productQuantity = rs.getInt("Stock");
                String productCategory = rs.getString("productCategory");
                double productPrice = rs.getDouble("productPrice");
                productQuantityView.printNotEnoughProductQuantity(productName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
