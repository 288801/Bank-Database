package db;

import exceptions.UserNotFoundException;
import models.Role;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class Users implements Table<User>{

    private List<User> users;
    private static Users instance;

    public static Users getInstance() {
        if (instance == null)
            instance = new Users();

        return instance;
    }

    private Users() {
        users = new ArrayList<>();
    }

    @Override
    public List<User> getTable() {
        return users;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User getById(String id) throws UserNotFoundException {
        for(User user : users){
            if(user.getEmail().equals(id)){
                return user;
            }
        }

        throw new UserNotFoundException();
    }

    @Override
    public void removeById(String id) throws UserNotFoundException {
        try{
            users.remove(getById(id));
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }
}
