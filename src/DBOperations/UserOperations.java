package DBOperations;

import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperations {
    public static User loginUser(String username, String password)
    {
        User loggedUser = null;
        DbConnection connection = new DbConnection();
        Connection connectDB = connection.connect();

        String verifyLogin = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(verifyLogin);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                loggedUser = new User(rs.getString(1), rs.getString(2),rs.getInt(3));
            }
            ps.close();
            rs.close();
            connectDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loggedUser;
    }

    public static boolean addUser(User user) {
        boolean isSuccessful = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            isSuccessful = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }

    public static boolean updateUser(User user) {
        boolean isSuccessful = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE users SET username = ?, password = ? WHERE ID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUserID());
            isSuccessful = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }

    public static boolean deleteUser(User user) {
        boolean isSuccessful = false;
        if(user == null)
        {
            return isSuccessful;
        }
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE from users WHERE ID = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            isSuccessful = ps.executeUpdate() == 1;
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }

    public static User findByID(int userID) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            String sql = "SELECT * FROM users WHERE ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getInt(3));
                rs.close();
                ps.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
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
}
