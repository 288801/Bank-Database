package services;

import exceptions.UserNotFoundException;
import models.BankAccount;
import models.Role;
import models.User;
import repositories.UserRepository;
import repositories.impl.UserRepositoryImpl;

import java.util.List;

public class UserDatabaseService {

    private UserRepository userRepository = UserRepositoryImpl.getInstance();
    private static UserDatabaseService instance;

    public static UserDatabaseService getInstance() {
        if (instance == null)
            instance = new UserDatabaseService();

        return instance;
    }

    private UserDatabaseService() {}

    public void addUser(String name, String surname, String email, String password){
        userRepository.add(new User(name, surname, email, password));
    }

    public void addAdmin(String name, String surname, String email, String password){
        userRepository.add(new User(name, surname, email, password, Role.ADMIN));
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.getById(email);
    }

    public boolean checkUser(String email){
        try{
            userRepository.getById(email);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getUserAccountsInfo(String email){
        List<BankAccount> accounts = AccountDatabaseService.getInstance().getUserAccounts(email);
        if(accounts.isEmpty()){
            return "You don't have accounts";
        }
        String result = "";
        for(BankAccount account : accounts){
            result += account.toString() + "\n";
        }
        return result;
    }

    public void removeUserByEmail(String email) throws UserNotFoundException {
        userRepository.removeById(email);
    }

}
