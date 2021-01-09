package Roles;

import DBOperations.DbConnection;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleManager {
    public Role getRole(User user)
    {
        Role role = null;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM UserRoles WHERE UserID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            rs = ps.executeQuery();
            if (rs.next()) {
                int roleID = rs.getInt("RoleID");
                switch (roleID) {
                    case 1:
                        role = Role.ADMIN;
                        break;
                    case 2:
                        role = Role.SELLER;
                        break;
                    case 3:
                        role = Role.USER;
                        break;

                    default:
                        break;
                }
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }
}
