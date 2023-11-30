package repositories.impl;

import db.ConnectionManager;
import models.Role;
import models.User;
import repositories.UserRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static ConnectionManager connectionManager;
    private static UserRepositoryImpl instance;

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
            connectionManager = ConnectionManager.getInstance();
        }
        return instance;
    }

    @Override
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

    @Override
    public void add(User user) {
        try {
            connectionManager.executeUpdate("INSERT INTO `user`(`email`, `name`, `surname`, `role`, `password`) VALUES ( '"
                    + user.getEmail() + "', '" + user.getName() + "', '" + user.getSurname()
                    + "', '" + (user.getRole()== Role.USER ? "USER" : "ADMIN") + "', '" + user.getPassword() + "')");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getById(String email) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `user` WHERE email = '" + email + "'");
            rs.next();
            User user = getUserFromResultSet(rs);
            rs.close();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(String email) {
        try {
            connectionManager.executeUpdate("DELETE FROM `user` WHERE email = '" + email + "'");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(String email, User user) {
        try {
            connectionManager.executeUpdate("UPDATE `user` SET name = '" + user.getName() + "', surname = "
                    + user.getSurname() + ", password = '" + user.getPassword() + "' WHERE email = '" + email + "'");
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
