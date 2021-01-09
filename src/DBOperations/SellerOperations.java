package DBOperations;

import Models.Product;
import Models.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class SellerOperations {
    public static boolean addSeller(Seller seller) {
        boolean isSuccessful = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps;
        try {
            String sql = "INSERT INTO Sellers(sellerUsername, sellerPassword) VALUES(?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, seller.getName());
            ps.setString(2, seller.getPassword());
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
            String sql = "SELECT seq FROM sqlite_sequence WHERE name = 'Sellers'";
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

    public static boolean updateSeller(Seller seller) {
        boolean isSuccessful = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps;
        try {
            String sql = "UPDATE Sellers SET sellerUsername = ?, sellerPassword = ? WHERE sellerID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, seller.getName());
            ps.setString(2, seller.getPassword());
            ps.setInt(3, seller.getSellerID());
            isSuccessful = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return isSuccessful;
    }

    public static Seller findByID(int sellerID) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Seller seller = null;
        try {
            String sql = "SELECT * FROM Sellers WHERE sellerID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, sellerID);
            rs = ps.executeQuery();
            if (rs.next()) {
                seller = new Seller(rs.getString(1), rs.getString(2), rs.getInt(3));

                rs.close();
                ps.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return seller;
    }

    public static boolean deleteSeller(Seller seller) {
        boolean isSuccessful = false;
        if(seller == null)
        {
            return isSuccessful;
        }
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE from Sellers WHERE SellerID = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, seller.getSellerID());
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

    public static void readSellers() {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * from Sellers";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sellerName = rs.getString("sellerUsername");
                System.out.printf("%s%n", sellerName);
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
                System.out.println(e.toString());
            }
        }

    }

}
