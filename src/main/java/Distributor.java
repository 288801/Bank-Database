import exceptions.IncorrectPasswordException;
import exceptions.UserNotFoundException;
import models.Role;
import services.roles.AdminService;
import services.roles.UserService;

public class Distributor {

    UserService userService = new UserService();
    AdminService adminService = new AdminService();

    public Role checkRole(String email, String password) throws UserNotFoundException, IncorrectPasswordException {
        if(adminService.checkRole(email, password)){
            return Role.ADMIN;
        }else if(userService.checkRole(email, password)){
            return Role.USER;
        }else{
            throw new IncorrectPasswordException();
        }
    }

}
