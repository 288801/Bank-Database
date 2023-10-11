package services;

import exceptions.UserNotFoundException;
import models.Role;
import models.User;
import operations.GetOperation;
import operations.PutOperation;
import operations.TransferOperation;

import java.util.Arrays;

public class AdminService implements RoleService{

    private DatabaseService db = new DatabaseService();

    @Override
    public boolean checkRole(String email, String password) throws UserNotFoundException {
        User user = db.getUserByEmail(email);
        if(user.checkPassword(password) && user.getRole() == Role.ADMIN){
            return true;
        }
        return false;
    }

    @Override
    public String processRequest(String email, String request) {
        String[] req = request.split(" ");
        String command = req[0];
        String[] params = Arrays.copyOfRange(req, 1, req.length);
        switch (command) {
            case "--help":
                return ("'--add_user name surname email password' - to add new user \n" +
                        "'--add_admin name surname email password' - to add new admin \n" +
                        "'--get_db' - to get all information about database \n" +
                        "'--get_info id' - to get information about account with id=id \n" +
                        "'--remove email' - to remove user with email=email");
            case "--add_user":
                try {
                    db.addUser(params[0], params[1], params[2], params[3]);
                    return ("Operation completed");
                } catch (Exception e) {
                    return ("The entered data is incorrect, please use the format 'name surname email password'");
                }
            case "--add_admin":
                try {
                    db.addAdmin(params[0], params[1], params[2], params[3]);
                    return ("Operation completed");
                } catch (Exception e) {
                    return ("The entered data is incorrect, please use the format 'name surname email password'");
                }
            case "--get_db":
                try {
                    return db.getDatabase();
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--get_info":
                try {
                    return db.getAccountInfo(email, Integer.parseInt(params[0]));
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--remove":
                try {
                    db.removeUserByEmail(params[0]);
                    return ("Operation completed");
                } catch (Exception e) {
                    return (e.getMessage());
                }
            default:
                return ("Input command is incorrect. To view a list of possible commands, enter '--help'");
        }
    }
}
