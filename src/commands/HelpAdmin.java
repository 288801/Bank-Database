package commands;

import services.AccountDatabaseService;
import services.DatabaseService;
import services.OperationDatabaseService;
import services.UserDatabaseService;

public class HelpAdmin implements Command{

    AccountDatabaseService accountDb = AccountDatabaseService.getInstance();
    UserDatabaseService userDb = UserDatabaseService.getInstance();
    OperationDatabaseService operationDb = OperationDatabaseService.getInstance();
    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        return ("'--add_user name surname email password' - to add new user \n" +
                "'--add_admin name surname email password' - to add new admin \n" +
                "'--get_db' - to get all information about database \n" +
                "'--get_info id' - to get information about account with id=id \n" +
                "'--remove email' - to remove user with email=email");
    }
}
