package commands;

import services.AccountDatabaseService;
import services.DatabaseService;
import services.OperationDatabaseService;
import services.UserDatabaseService;

public class AddAccount implements Command{

    AccountDatabaseService accountDb = AccountDatabaseService.getInstance();
    UserDatabaseService userDb = UserDatabaseService.getInstance();
    OperationDatabaseService operationDb = OperationDatabaseService.getInstance();
    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            accountDb.createAccount(email, Integer.parseInt(params[0]));
            return ("Operation completed");
        } catch (Exception e) {
            return ("The entered data is incorrect, please use the format '--add balance'");
        }
    }
}
