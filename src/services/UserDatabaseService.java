package services;

import db.Accounts;
import db.Operations;
import db.Users;
import exceptions.UserNotFoundException;
import models.BankAccount;
import models.Role;
import models.User;

import java.util.List;

public class UserDatabaseService {

    Users userDb = Users.getInstance();
    private static UserDatabaseService instance;

    public static UserDatabaseService getInstance() {
        if (instance == null)
            instance = new UserDatabaseService();

        return instance;
    }

    private UserDatabaseService() {}

    public void addUser(String name, String surname, String email, String password){
        userDb.add(new User(name, surname, email, password));
    }

    public void addAdmin(String name, String surname, String email, String password){
        userDb.add(new User(name, surname, email, password, Role.ADMIN));
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userDb.getById(email);
    }

    public boolean checkUser(String email){
        try{
            userDb.getById(email);
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
        userDb.removeById(email);
    }

}
