package models;

import db.Operations;
import operations.OperationImpl;
import services.DatabaseService;
import services.OperationDatabaseService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {
    private int accountId;
    private User owner;
    private int balance;

    public BankAccount(int accountId, User user, int balance) {
        this.accountId = accountId;
        this.owner = user;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount_" + accountId + "{" +
                "ownerName='" + owner.getName() +
                "', ownerSurname='" + owner.getSurname() +
                "', balance=" + balance +
                ", operationHistory=" + OperationDatabaseService.getInstance().getAllAccountOperations(accountId) +
                '}';
    }

    public String getOwnerName() {return owner.getName();}

    public String getOwnerSurname() {return owner.getSurname();}

    public String getOwnerEmail(){return owner.getEmail();}

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }
}
