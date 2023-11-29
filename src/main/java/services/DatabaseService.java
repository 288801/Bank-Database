package services;

import models.BankAccount;
import models.User;
import operations.Operation;
import repositories.AccountRepository;
import repositories.OperationRepository;
import repositories.UserRepository;

public class DatabaseService {

    private UserRepository userRepository = UserRepository.getInstance();
    private AccountRepository accountRepository = AccountRepository.getInstance();
    private OperationRepository operationRepository = OperationRepository.getInstance();
    private static DatabaseService instance;

    public static DatabaseService getInstance() {
        if (instance == null)
            instance = new DatabaseService();

        return instance;
    }

    private DatabaseService() {}

    public String getDatabase(){
        if(userRepository.getAll().isEmpty() && accountRepository.getAll().isEmpty()){
            return "Database is empty";
        }
        String result = "";
        for(User user : userRepository.getAll()){
            result += user.toString() + "\n";
        }
        for(BankAccount account : accountRepository.getAll()){
            result += account.toString() + "\n";
        }
        for(Operation operation : operationRepository.getAll()){
            result += operation.toString() + "\n";
        }
        return result;
    }
}
