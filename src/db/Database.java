package db;

import models.BankAccount;
import exceptions.UserNotFoundException;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<BankAccount> accounts;
    private List<User> users;
    private static Database instance;

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();

        return instance;
    }

    private Database() {
        accounts = new ArrayList<>();
        users = new ArrayList<>();
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addAccount(BankAccount account){accounts.add(account);}

    public void addUser(User user){users.add(user);}
}
