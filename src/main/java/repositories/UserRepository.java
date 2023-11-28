package repositories;

import db.ConnectionManager;
import models.Role;
import models.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static ConnectionManager connectionManager;
    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
            connectionManager = ConnectionManager.getInstance();
        }
        return instance;
    }

    public List<User> getAll() {
        try{
            List<User> users = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM USER");
            while (rs.next()){
                users.add(getUserFromResultSet(rs));
            }
            rs.close();
            return users;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void add(User user) {
        try {
            connectionManager.executeUpdate("INSERT INTO USER (email, name, surname, role, password) VALUES ( '"
                    + user.getEmail() + "', " + user.getName() + ", '" + user.getSurname()
                    + ", '" + (user.getRole()== Role.USER ? "USER" : "ADMIN") + ", '" + user.getPassword() + "');");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public User getByEmail(String email) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM USER WHERE email = " + email);
            rs.next();
            User user = getUserFromResultSet(rs);
            rs.close();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void removeByEmail(String email) {
        try {
            connectionManager.executeUpdate("DELETE FROM USER WHERE email = " + email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String email, User user) {
        try {
            connectionManager.executeUpdate("UPDATE USER SET name = '" + user.getName() + "', surname = "
                    + user.getSurname() + ", password = '" + user.getPassword() + "' WHERE email = " + email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private User getUserFromResultSet(ResultSet rs) {
        try {
            String email = rs.getString(1);
            String name = rs.getString(2);
            String surname  = rs.getString(3);
            String role = rs.getString(4);
            String password = rs.getString(5);
            return new User(name, surname, email, password, role.equals("USER") ? Role.USER : Role.ADMIN);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
