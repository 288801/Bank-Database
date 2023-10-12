package commands;

import operations.PutOperation;
import services.DatabaseService;

public class AddMoney implements Command{

    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            int id = db.operationDb.size();
            PutOperation operation = new PutOperation(id, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
            operation.doOperation();
            return ("Operation completed");
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
