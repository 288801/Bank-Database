package services;

import exceptions.UserNotFoundException;
import models.Role;
import models.User;
import operations.GetOperation;
import operations.PutOperation;
import operations.TransferOperation;

import java.util.Arrays;

public class UserService implements RoleService{

    private DatabaseService db = DatabaseService.getInstance();

    @Override
    public boolean checkRole(String email, String password) throws UserNotFoundException {
        User user = db.getUserByEmail(email);
        if(user.checkPassword(password) && user.getRole() == Role.USER){
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
                return ("'--add balance' - to add new bank account \n" +
                        "'--remove id' - to remove bank account by id \n" +
                        "'--get_info id' - to get information about your account with id=id \n" +
                        "'--get_all_info' - to get information about your bank accounts \n" +
                        "'--add_money sum id' - to add money in bank account \n" +
                        "'--get_money sum id' - to get money from account \n" +
                        "'--transfer sum id1 id2' - to transfer money from first account to second");
            case "--add":
                try {
                    db.createAccount(email, Integer.parseInt(params[0]));
                    return ("Operation completed");
                } catch (Exception e) {
                    return ("The entered data is incorrect, please use the format '--add balance'");
                }
            case "--remove":
                try {
                    int id = Integer.parseInt(params[0]);
                    db.removeAccountById(id);
                    return ("Operation completed");
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--get_info":
                try {
                    return db.getAccountInfo(email, Integer.parseInt(params[0]));
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--get_all_info":
                try {
                    return db.getUserAccountsInfo(email);
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--add_money":
                try {
                    int id = db.operationDb.size();
                    PutOperation operation = new PutOperation(id, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
                    operation.doOperation();
                    return ("Operation completed");
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--get_money":
                try {
                    int id = db.operationDb.size();
                    GetOperation operation = new GetOperation(id, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
                    operation.doOperation();
                    return ("Operation completed");
                } catch (Exception e) {
                    return (e.getMessage());
                }
            case "--transfer":
                try {
                    int id = db.operationDb.size();
                    TransferOperation operation = new TransferOperation(id, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]));
                    operation.doOperation();
                    return ("Operation completed");
                } catch (Exception e) {
                    return (e.getMessage());
                }
            default:
                return ("Input command is incorrect. To view a list of possible commands, enter '--help'");
        }
    }
}
