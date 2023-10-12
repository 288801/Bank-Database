package services;

import db.Accounts;
import db.Operations;
import db.Users;
import exceptions.BankAccountNotFoundException;
import exceptions.UserNotFoundException;
import models.BankAccount;
import models.Role;

import java.util.ArrayList;
import java.util.List;

public class AccountDatabaseService {

    Accounts accountDb = Accounts.getInstance();
    private static AccountDatabaseService instance;

    public static AccountDatabaseService getInstance() {
        if (instance == null)
            instance = new AccountDatabaseService();

        return instance;
    }

    private AccountDatabaseService() {}

    public void createAccount(String email, int balance) throws UserNotFoundException {
        int id = accountDb.size();
        accountDb.add(new BankAccount(id, UserDatabaseService.getInstance().getUserByEmail(email), balance));
    }

    public BankAccount getAccountById(int id) throws BankAccountNotFoundException {
        return accountDb.getById(String.valueOf(id));
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

    public String getAccountInfo(String email, int id) throws BankAccountNotFoundException {
        try {
            BankAccount account = accountDb.getById(String.valueOf(id));
            if(account.getOwnerEmail().equals(email) || UserDatabaseService.getInstance().getUserByEmail(email).getRole() == Role.ADMIN){
                return account.toString();
            }
            return "You don't have permission to view this information";
        }catch (Exception e){
            throw new BankAccountNotFoundException();
        }
    }

    public void removeAccountById(int id) throws BankAccountNotFoundException {
        accountDb.removeById(String.valueOf(id));
    }

}
