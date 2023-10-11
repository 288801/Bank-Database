package services;

import db.Database;
import exceptions.BankAccountNotFoundException;
import exceptions.UserNotFoundException;
import models.BankAccount;
import models.Role;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    private Database db = Database.getInstance();

    public void addUser(String name, String surname, String email, String password){
        db.addUser(new User(name, surname, email, password));
    }

    public void addAdmin(String name, String surname, String email, String password){
        db.addUser(new User(name, surname, email, password, Role.ADMIN));
    }

    public void createAccount(String email, int balance) throws UserNotFoundException {
        int id = db.getAccounts().size();
        db.addAccount(new BankAccount(id, getUserByEmail(email), balance));
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        for(User user : db.getUsers()){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    public boolean checkUser(String email){
        for(User user : db.getUsers()){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public BankAccount getAccountById(int id) throws BankAccountNotFoundException {
        try {
            return db.getAccounts().get(id);
        }catch (Exception e){
            throw new BankAccountNotFoundException();
        }
    }

    public List<BankAccount> getUserAccounts(String email){
        List<BankAccount> accounts = new ArrayList<>();
        for(BankAccount account : db.getAccounts()){
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
            BankAccount account = db.getAccounts().get(id);
            if(account.getOwnerEmail().equals(email) || getUserByEmail(email).getRole() == Role.ADMIN){
                return account.toString();
            }
            return "You don't have permission to view this information";
        }catch (Exception e){
            throw new BankAccountNotFoundException();
        }
    }

    public String getDatabase(){
        if(db.getAccounts().isEmpty() && db.getUsers().isEmpty()){
            return "Database is empty";
        }
        String result = "";
        for(User user : db.getUsers()){
            result += user.toString() + "\n";
        }
        for(BankAccount account : db.getAccounts()){
            result += account.toString() + "\n";
        }
        return result;
    }

    public void removeAccountById(int id) throws UserNotFoundException {
        try{
            db.getAccounts().remove(id);
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    public void removeUserByEmail(String email) throws UserNotFoundException {
        try{
            db.getAccounts().remove(getUserByEmail(email));
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }
}
