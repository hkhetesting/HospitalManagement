package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import puf.m2.hms.model.*;

public class User {

    ResultSet rs;

    private String username;
    private String password;
    private String useremail;
    private String usertypeCode;
    
    public User(ResultSet rs) {
        this.rs = rs;
    }

    public User() {

    }

    public boolean login(String username, String password) {

        boolean result = false;

        DatabaseAbstract db = new DatabaseImpl();
        try {
            db.createConnection();
            db.createStatement();
            rs = db.getResultSet("SELECT username FROM User WHERE username='"
                    + username + "' " + "and password = '" + password + "'");

            db.closeConnection();
            // if (rs==null) then login unsucessful
            return (rs != null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUseremail() {
        return useremail;
    }
    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
    public String getUsertypeCode() {
        return usertypeCode;
    }
    public void setUsertypeCode(String usertypeCode) {
        this.usertypeCode = usertypeCode;
    }

}
