package repositories.impl;

import db.ConnectionManager;
import models.BankAccount;
import operations.OperationImpl;
import repositories.AccountRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    private static ConnectionManager connectionManager;
    private static AccountRepositoryImpl instance;
    private UserRepositoryImpl userRepository = UserRepositoryImpl.getInstance();

    public static AccountRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new AccountRepositoryImpl();
            connectionManager = ConnectionManager.getInstance();
        }
        return instance;
    }

    @Override
    public List<BankAccount> getAll() {
        try{
            List<BankAccount> accounts = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `account`");
            while (!rs.isClosed() && rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
            rs.close();
            return accounts;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void add(BankAccount bankAccount) {
        try {
            connectionManager.executeUpdate("INSERT INTO `account`(`owner_id`, `balance`) VALUES ( '"
                    + bankAccount.getOwnerEmail() + "', " + bankAccount.getBalance() + ")");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public BankAccount getById(Integer id) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `account` WHERE account_id = " + id);
            rs.next();
            BankAccount account = getAccountFromResultSet(rs);
            rs.close();
            return account;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Integer id) {
        try {
            connectionManager.executeUpdate("DELETE FROM `account` WHERE account_id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Integer id, BankAccount account) {
        try {
            connectionManager.executeUpdate("UPDATE `account` SET `account_id`=" + id + "," +
                    "`owner_id`='" + account.getOwnerEmail() + "',`balance`=" + account.getBalance() + " WHERE account_id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private BankAccount getAccountFromResultSet(ResultSet rs) {
        try {
            int id = rs.getInt(1);
            String ownerEmail = rs.getString(2);
            int balance = rs.getInt(3);
            return new BankAccount(id, userRepository.getById(ownerEmail), balance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<BankAccount> getUserAccounts(String email) {
        try{
            List<BankAccount> accounts = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `account` WHERE owner_id = '" + email + "'");
            while (!rs.isClosed() && rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
            rs.close();
            return accounts;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
