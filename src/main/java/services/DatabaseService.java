package services;

import db.Accounts;
import db.Operations;
import db.Users;
import exceptions.BankAccountNotFoundException;
import exceptions.UserNotFoundException;
import models.BankAccount;
import models.Role;
import models.User;
import operations.Operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
