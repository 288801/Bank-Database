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
    private User admin;

    @Override
    public boolean checkRole(String email, String password) throws UserNotFoundException {
        User user = db.getUserByEmail(email);
        if(user.checkPassword(password) && user.getRole() == Role.ADMIN){
            return true;
        }
        return false;
    }

    @Override
    public String processRequest(String request) {
        String[] req = request.split(" ");
        String command = req[0];
        String[] params = Arrays.copyOfRange(req, 1, req.length);
        switch (command) {
            case "--help":
                return ("'--end' - to stop the program \n" +
                        "'--add name surname balance' - to add user \n" +
                        "'--remove_id id' - to remove user by id \n" +
                        "'--remove_name name surname' - to remove user by name \n" +
                        "'--get_db' - to print database \n" +
                        "'--get_user_id id' - to get user by id \n" +
                        "'--get_user_name name surname' - to get user by name \n" +
                        "'--add_money name surname sum' - to add money \n" +
                        "'--get_money name surname sum' - to get money from account \n" +
                        "'--transfer name1 surname1 name2 surname2 sum' - to transfer money from first account to second");
            case "--end":
                return null;
            case "--add": try {
                db.addUser(params[0], params[1], Integer.parseInt(params[2]));
                return ("Operation completed");
            } catch (Exception e) {
                return ("The entered data is incorrect, please use the format 'name surname balance'");
            }
            case "--remove_id":
                try {
                    int id = Integer.parseInt(params[0]);
                    db.removeById(id);
                    return ("Operation completed");
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--remove_name":
                try {
                    db.removeByName(params[0], params[1]);
                    return ("Operation completed");
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--get_db":
                return (db.getDatabase());
            case "--get_user_id":
                try {
                    int id = Integer.parseInt(params[0]);
                    return (db.getUserById(id).toString());
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--get_user_name":
                try {
                    return (db.getUserByName(params[0], params[1]).toString());
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--add_money":
                try {
                    PutOperation operation = new PutOperation(Integer.parseInt(params[2]), params[0], params[1]);
                    operation.doOperation();
                    return ("Operation completed");
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--get_money":
                try {
                    GetOperation operation = new GetOperation(Integer.parseInt(params[2]), params[0], params[1]);
                    operation.doOperation();
                    return ("Operation completed");
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--transfer":
                try {
                    TransferOperation operation = new TransferOperation(Integer.parseInt(params[4]), params[0], params[1], params[2], params[3]);
                    operation.doOperation();
                    return ("Operation completed");
                } catch (Exception e) {
                    return (e.getMessage());
                }
            default:
                return ("Input command is incorrect. To view a list of possible commands, enter '--help'");
    }
}
