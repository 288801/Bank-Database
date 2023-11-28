package commands;

import operations.TransferOperation;
import services.AccountDatabaseService;
import services.DatabaseService;
import services.OperationDatabaseService;
import services.UserDatabaseService;

public class TransferMoney implements Command{

    AccountDatabaseService accountDb = AccountDatabaseService.getInstance();
    UserDatabaseService userDb = UserDatabaseService.getInstance();
    OperationDatabaseService operationDb = OperationDatabaseService.getInstance();
    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            int id = db.operationDb.size();
            TransferOperation operation = new TransferOperation(id, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            operation.doOperation();
            return ("Operation completed");
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
