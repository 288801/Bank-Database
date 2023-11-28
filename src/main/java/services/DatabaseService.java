package services;

import models.BankAccount;
import models.User;
import operations.Operation;

public class DatabaseService {

    Users userDb = Users.getInstance();
    Accounts accountDb = Accounts.getInstance();
    public Operations operationDb = Operations.getInstance();
    private static DatabaseService instance;

    public static DatabaseService getInstance() {
        if (instance == null)
            instance = new DatabaseService();

        return instance;
    }

    private DatabaseService() {}

    public String getDatabase(){
        if(userDb.getTable().isEmpty() && accountDb.getTable().isEmpty()){
            return "Database is empty";
        }
        String result = "";
        for(User user : userDb.getTable()){
            result += user.toString() + "\n";
        }
        for(BankAccount account : accountDb.getTable()){
            result += account.toString() + "\n";
        }
        for(Operation operation : operationDb.getTable()){
            result += operation.toString() + "\n";
        }
        return result;
    }
}
