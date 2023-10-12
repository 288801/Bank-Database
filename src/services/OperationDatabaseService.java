package services;

import db.Accounts;
import db.Operations;
import db.Users;
import operations.Operation;

import java.util.ArrayList;
import java.util.List;

public class OperationDatabaseService {

    Operations operationDb = Operations.getInstance();
    private static OperationDatabaseService instance;

    public static OperationDatabaseService getInstance() {
        if (instance == null)
            instance = new OperationDatabaseService();

        return instance;
    }

    private OperationDatabaseService() {}

    public List<Operation> getAllAccountOperations(int id){
        List<Operation> result = new ArrayList<>();
        for(Operation operation : operationDb.getTable()){
            if(operation.getId() ==  id){
                result.add(operation);
            }
        }

        return result;
    }

}
