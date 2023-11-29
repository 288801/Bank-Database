package repositories;

import db.ConnectionManager;
import models.BankAccount;
import models.Role;
import models.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private static ConnectionManager connectionManager;
    private static AccountRepository instance;
    private UserRepository userRepository = UserRepository.getInstance();

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
            connectionManager = ConnectionManager.getInstance();
        }
        return instance;
    }

    public List<BankAccount> getAll() {
        try{
            List<BankAccount> accounts = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM ACCOUNT");
            while (rs.next()){
                accounts.add(getAccountFromResultSet(rs));
            }
            rs.close();
            return accounts;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void add(BankAccount bankAccount) {
        try {
            connectionManager.executeUpdate("INSERT INTO `account`(`owner_id`, `balance`) VALUES ( '"
                    + bankAccount.getOwnerEmail() + ", '" + bankAccount.getBalance() + "');");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public BankAccount getById(int id) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM ACCOUNT WHERE account_id = " + id);
            rs.next();
            BankAccount account = getAccountFromResultSet(rs);
            rs.close();
            return account;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void removeById(int id) {
        try {
            connectionManager.executeUpdate("DELETE FROM ACCOUNT WHERE account_id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, BankAccount account) {
        try {
            connectionManager.executeUpdate("UPDATE ACCOUNT SET owner_id = '" + account.getOwnerName() + "', balance = "
                    + account.getBalance() + "' WHERE account_id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private BankAccount getAccountFromResultSet(ResultSet rs) {
        try {
            int id = rs.getInt(1);
            String ownerEmail = rs.getString(2);
            int balance = rs.getInt(3);
            return new BankAccount(id, userRepository.getByEmail(ownerEmail), balance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
