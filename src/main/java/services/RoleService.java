package services;

import exceptions.UserNotFoundException;

public interface RoleService {

    public boolean checkRole(String email, String password) throws UserNotFoundException;

    public String processRequest(String email, String request);
}
