package services;

import commands.*;
import db.Accounts;
import db.Operations;
import db.Users;
import exceptions.UserNotFoundException;
import models.Role;
import models.User;
import operations.GetOperation;
import operations.PutOperation;
import operations.TransferOperation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserService implements RoleService{

    AccountDatabaseService accountDb = AccountDatabaseService.getInstance();
    UserDatabaseService userDb = UserDatabaseService.getInstance();
    OperationDatabaseService operationDb = OperationDatabaseService.getInstance();
    DatabaseService db = DatabaseService.getInstance();

    static Map<String, Command> commands = new HashMap<>(){{
        put("--help", new HelpUser());
        put("--add", new AddAccount());
        put("--get_info", new GetAccountInfo());
        put("--remove", new RemoveAccount());
        put("--get_all_info", new GetAllAccountInfo());
        put("--add_money", new AddMoney());
        put("--get_money", new GetMoney());
        put("--transfer", new TransferMoney());
    }};

    @Override
    public boolean checkRole(String email, String password) throws UserNotFoundException {
        User user = userDb.getUserByEmail(email);
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
        return commands.get(command).execute(email, params);
    }
}
