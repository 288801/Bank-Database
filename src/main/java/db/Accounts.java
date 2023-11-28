package db;

import exceptions.BankAccountNotFoundException;
import exceptions.UserNotFoundException;
import models.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class Accounts implements Table<BankAccount>{

    private List<BankAccount> accounts;
    private static Accounts instance;

    public static Accounts getInstance() {
        if (instance == null)
            instance = new Accounts();

        return instance;
    }

    private Accounts() {
        accounts = new ArrayList<>();
    }

    @Override
    public List<BankAccount> getTable() {
        return accounts;
    }

    @Override
    public void add(BankAccount account) throws UserNotFoundException {
        accounts.add(account);
    }

    @Override
    public BankAccount getById(String id) throws BankAccountNotFoundException {
        try {
            return accounts.get(Integer.parseInt(id));
        }catch (Exception e){
            throw new BankAccountNotFoundException();
        }
    }

    @Override
    public void removeById(String id) throws BankAccountNotFoundException {
        try{
            accounts.remove(Integer.parseInt(id));
        }catch (Exception e){
            throw new BankAccountNotFoundException();
        }
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "accounts=" + accounts +
                '}';
    }

    public int size(){
        return accounts.size();
    }
}
