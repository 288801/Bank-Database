package services;

import exceptions.BankAccountNotFoundException;
import exceptions.UserNotFoundException;
import models.BankAccount;
import models.Role;
import repositories.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class AccountDatabaseService {

    private AccountRepository accountRepository = AccountRepository.getInstance();
    private static AccountDatabaseService instance;

    public static AccountDatabaseService getInstance() {
        if (instance == null)
            instance = new AccountDatabaseService();

        return instance;
    }

    private AccountDatabaseService() {}

    public void createAccount(String email, int balance) throws UserNotFoundException {
        accountRepository.add(new BankAccount(0, UserDatabaseService.getInstance().getUserByEmail(email), balance));
    }

    public BankAccount getAccountById(int id) throws BankAccountNotFoundException {
        return accountRepository.getById(id);
    }

    public List<BankAccount> getUserAccounts(String email){
        List<BankAccount> accounts = new ArrayList<>();
        for(BankAccount account : accountRepository.getAll()){
            if(account.getOwnerEmail().equals(email)){
                accounts.add(account);
            }
        }
        return accounts;
    }

    public String getAccountInfo(String email, int id) throws BankAccountNotFoundException {
        try {
            BankAccount account = accountRepository.getById(id);
            if(account.getOwnerEmail().equals(email) || UserDatabaseService.getInstance().getUserByEmail(email).getRole() == Role.ADMIN){
                return account.toString();
            }
            return "You don't have permission to view this information";
        }catch (Exception e){
            throw new BankAccountNotFoundException();
        }
    }

    public void removeAccountById(int id) throws BankAccountNotFoundException {
        accountRepository.removeById(id);
    }

}
