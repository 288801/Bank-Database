package commands;

import services.AccountDatabaseService;
import services.DatabaseService;
import services.OperationDatabaseService;
import services.UserDatabaseService;

public class GetAllAccountInfo implements Command{

    AccountDatabaseService accountDb = AccountDatabaseService.getInstance();
    UserDatabaseService userDb = UserDatabaseService.getInstance();
    OperationDatabaseService operationDb = OperationDatabaseService.getInstance();
    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            return userDb.getUserAccountsInfo(email);
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
