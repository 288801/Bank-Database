package commands;

import operations.TransferOperation;
import services.DatabaseService;

public class TransferMoney implements Command{

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
