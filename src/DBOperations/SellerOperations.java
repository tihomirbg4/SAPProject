package DBOperations;
import Models.Order;
import Models.Product;
import Models.User;
import Roles.Role;
import Security.PasswordManager;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerOperations {
    public static boolean addSeller(User seller) {
        boolean isSuccessful = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps;
        PreparedStatement ps1;
        try {
            String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
            String sql1 = "INSERT INTO UserRoles(UserID, RoleID) VALUES(?, ?)";
            ps = con.prepareStatement(sql);
            ps1 = con.prepareStatement(sql1);
            ps.setString(1, seller.getUsername());
            ps.setString(2, PasswordManager.hashPassword(seller.getPassword()));
            ps1.setInt(1, getAutoIncrementValue() +1);
            ps1.setInt(2, Role.SELLER.ordinal() + 1);
            isSuccessful = ps.executeUpdate() == 1;
            ps1.execute();
        } catch (SQLException | NoSuchAlgorithmException e) {
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
            String sql = "SELECT seq FROM sqlite_sequence WHERE name = 'users'";
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

    public static boolean updateSeller(User seller) {
        boolean isSuccessful = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps;
        try {
            String sql = "UPDATE users SET username = ?, password = ? WHERE ID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, seller.getUsername());
            ps.setString(2, PasswordManager.hashPassword(seller.getPassword()));
            ps.setInt(3, seller.getUserID());
            isSuccessful = ps.executeUpdate() == 1;
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }

    public static User findByID(int sellerID) {

        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User seller = null;
        try {
            String sql = "SELECT users.username, users.password, users.ID, UserRoles.UserID, UserRoles.RoleID FROM users INNER JOIN UserRoles ON UserRoles.UserID = ? AND users.ID = ? WHERE UserRoles.RoleID = 2";
            ps = con.prepareStatement(sql);
            ps.setInt(1, sellerID);
            ps.setInt(2, sellerID);
            rs = ps.executeQuery();
            if (rs.next()) {
                seller = new User(rs.getString(1), rs.getString(2), rs.getInt(3));

                rs.close();
                ps.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seller;
    }

    public static boolean deleteSeller(User seller) {
        boolean isSuccessful = false;
        if(seller == null)
        {
            return isSuccessful;
        }
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        try {
            String sql = "DELETE from users WHERE ID = ? ";
            String sql1 = "DELETE FROM UserRoles WHERE UserID = ?";
            ps = con.prepareStatement(sql);
            ps1 = con.prepareStatement(sql1);
            ps.setInt(1, seller.getUserID());
            ps1.setInt(1, seller.getUserID());
            isSuccessful = ps.executeUpdate() == 1;
            ps1.execute();
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

    public static void readSellers() {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT username, ID from users INNER JOIN UserRoles ON users.ID = UserRoles.UserID WHERE RoleID = 2";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sellerName = rs.getString("username");
                int sellerID = rs.getInt("ID");
                System.out.printf("%s with ID %d%n", sellerName, sellerID);

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                assert rs != null;
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static User findSellerInSoldProducts(int sellerID) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User seller = null;
        try {
            String sql = "SELECT * FROM SoldProducts WHERE sellerID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, sellerID);
            rs = ps.executeQuery();
            if (rs.next()) {
                seller = new User(rs.getString(1), rs.getString(2), rs.getInt(3));

                rs.close();
                ps.close();
                con.close();
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return seller;
    }

}
