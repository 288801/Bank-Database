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

    public void addUser(String name, String surname, String email, String password){
        userDb.add(new User(name, surname, email, password));
    }

    public void addAdmin(String name, String surname, String email, String password){
        userDb.add(new User(name, surname, email, password, Role.ADMIN));
    }

    public void createAccount(String email, int balance) throws UserNotFoundException {
        int id = accountDb.size();
        accountDb.add(new BankAccount(id, userDb.getById(email), balance));
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

    public BankAccount getAccountById(int id) throws BankAccountNotFoundException {
        return accountDb.getById(String.valueOf(id));
    }

    public List<Operation> getAllAccountOperations(int id){
        List<Operation> result = new ArrayList<>();
        for(Operation operation : operationDb.getTable()){
            if(operation.getId() ==  id){
                result.add(operation);
            }
        }

        return result;
    }

    public List<BankAccount> getUserAccounts(String email){
        List<BankAccount> accounts = new ArrayList<>();
        for(BankAccount account : accountDb.getTable()){
            if(account.getOwnerEmail().equals(email)){
                accounts.add(account);
            }
        }
        return accounts;
    }

    public String getUserAccountsInfo(String email){
        List<BankAccount> accounts = getUserAccounts(email);
        if(accounts.isEmpty()){
            return "You don't have accounts";
        }
        String result = "";
        for(BankAccount account : accounts){
            result += account.toString() + "\n";
        }
        return result;
    }

    public String getAccountInfo(String email, int id) throws BankAccountNotFoundException {
        try {
            BankAccount account = accountDb.getById(String.valueOf(id));
            if(account.getOwnerEmail().equals(email) || getUserByEmail(email).getRole() == Role.ADMIN){
                return account.toString();
            }
            return "You don't have permission to view this information";
        }catch (Exception e){
            throw new BankAccountNotFoundException();
        }
    }

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

    public void removeAccountById(int id) throws UserNotFoundException {

    }

    public void removeUserByEmail(String email) throws UserNotFoundException {

    }
}
