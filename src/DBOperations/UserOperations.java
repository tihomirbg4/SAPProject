package DBOperations;

import Models.User;
import Roles.Role;
import Security.PasswordManager;

import java.security.NoSuchAlgorithmException;
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
            ps.setString(2, PasswordManager.hashPassword(password));
            String hashedPassword = PasswordManager.hashPassword(password);
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
        PreparedStatement ps1 = null;
        try {
            String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
            String sql1 = "INSERT INTO UserRoles(UserID, RoleID) VALUES(?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, PasswordManager.hashPassword(user.getPassword()));
            ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, getAutoIncrementValue() + 1);
            ps1.setInt(2, Role.USER.ordinal() + 1);
            isSuccessful = ps.executeUpdate() == 1;
            ps1.execute();
        } catch (SQLException | NoSuchAlgorithmException e) {
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
            ps.setString(2, PasswordManager.hashPassword(user.getPassword()));
            ps.setInt(3, user.getUserID());
            isSuccessful = ps.executeUpdate() == 1;
        } catch (SQLException | NoSuchAlgorithmException e) {
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
        PreparedStatement ps1 = null;
        try {
            String sql = "DELETE from users WHERE ID = ? ";
            String sql1 = "DELETE FROM UserRoles WHERE UserID = ?";
            ps = con.prepareStatement(sql);
            ps1 = con.prepareStatement(sql1);
            ps.setInt(1, user.getUserID());
            ps1.setInt(1, user.getUserID());
            isSuccessful = ps.executeUpdate() == 1;
            ps1.execute();
            ps.close();
            ps1.close();
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
            String sql = "SELECT users.username, users.password, users.ID, UserRoles.UserID, UserRoles.RoleID FROM users INNER JOIN UserRoles ON UserRoles.UserID = ? AND users.ID = ? WHERE UserRoles.RoleID = 3";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, userID);
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
