package services;

import operations.Operation;
import operations.OperationImpl;
import repositories.OperationRepository;
import repositories.impl.OperationRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class OperationDatabaseService {

    private OperationRepository operationRepository = OperationRepositoryImpl.getInstance();
    private static OperationDatabaseService instance;

    public static OperationDatabaseService getInstance() {
        if (instance == null)
            instance = new OperationDatabaseService();

        return instance;
    }

    private OperationDatabaseService() {}

    public List<OperationImpl> getAllAccountOperations(int id){
        return operationRepository.getAllAccountOperations(id);
    }

    public void addOperation(OperationImpl operation){
        operationRepository.add(operation);
    }

}
