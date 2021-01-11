package Controllers;

import Models.User;

public class UserSession {
    private User user;

    public UserSession(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public String getUsername()
    {
        return user.getUsername();
    }

    public String getPassword()
    {
        return user.getPassword();
    }
}
