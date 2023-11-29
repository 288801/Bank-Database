package services;

import operations.Operation;
import operations.OperationImpl;
import repositories.OperationRepository;

import java.util.ArrayList;
import java.util.List;

public class OperationDatabaseService {

    private OperationRepository operationRepository = OperationRepository.getInstance();
    private static OperationDatabaseService instance;

    public static OperationDatabaseService getInstance() {
        if (instance == null)
            instance = new OperationDatabaseService();

        return instance;
    }

    private OperationDatabaseService() {}

    public List<Operation> getAllAccountOperations(int id){
        List<Operation> result = new ArrayList<>();
        for(Operation operation : operationRepository.getAll()){
            if(operation.getId() ==  id){
                result.add(operation);
            }
        }

        return result;
    }

    public void addOperation(OperationImpl operation){
        operationRepository.add(operation);
    }

}
