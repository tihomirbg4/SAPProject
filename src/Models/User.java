package Models;

import DBOperations.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String username;
    private String password;
    private int id;

    public User(String username, String password, int userID) {
        this.username = username;
        this.password = password;
        this.id = userID;
    }

    public User(String username, int userID)
    {
        this.username = username;
        this.id = userID;
    }

    public String getPassword() {
        return this.password;
    }

    public int getUserID() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

}
