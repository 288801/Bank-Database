package commands;

import services.AccountDatabaseService;
import services.DatabaseService;
import services.OperationDatabaseService;
import services.UserDatabaseService;

public class HelpUser implements Command{

    AccountDatabaseService accountDb = AccountDatabaseService.getInstance();
    UserDatabaseService userDb = UserDatabaseService.getInstance();
    OperationDatabaseService operationDb = OperationDatabaseService.getInstance();
    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        return ("'--add balance' - to add new bank account \n" +
                "'--remove id' - to remove bank account by id \n" +
                "'--get_info id' - to get information about your account with id=id \n" +
                "'--get_all_info' - to get information about your bank accounts \n" +
                "'--add_money sum id' - to add money in bank account \n" +
                "'--get_money sum id' - to get money from account \n" +
                "'--transfer sum id1 id2' - to transfer money from first account to second");
    }
}
