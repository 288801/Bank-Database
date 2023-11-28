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

public class AdminService implements RoleService{

    AccountDatabaseService accountDb = AccountDatabaseService.getInstance();
    UserDatabaseService userDb = UserDatabaseService.getInstance();
    OperationDatabaseService operationDb = OperationDatabaseService.getInstance();
    DatabaseService db = DatabaseService.getInstance();

    static Map<String, Command> commands = new HashMap<>(){{
        put("--help", new HelpAdmin());
        put("--add_user", new AddUser());
        put("--add_admin", new AddAdmin());
        put("--get_db", new GetDb());
        put("--get_info", new GetAccountInfo());
        put("--remove", new RemoveUser());
    }};

    @Override
    public boolean checkRole(String email, String password) throws UserNotFoundException {
        User user = userDb.getUserByEmail(email);
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
        return commands.get(command).execute(email, params);
    }
}
