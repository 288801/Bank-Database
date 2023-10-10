package services;

import exceptions.UserNotFoundException;
import models.Role;
import models.User;

public class UserService implements RoleService{

    private DatabaseService db = new DatabaseService();

    @Override
    public boolean checkRole(String email, String password) throws UserNotFoundException {
        User user = db.getUserByEmail(email);
        if(user.checkPassword(password) && user.getRole() == Role.USER){
            return true;
        }
        return false;
    }

    @Override
    public String processRequest(String request) {
        return null;
    }
}
