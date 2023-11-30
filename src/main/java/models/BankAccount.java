package models;

import repositories.AccountRepository;
import repositories.impl.AccountRepositoryImpl;
import services.OperationDatabaseService;

public class BankAccount {

    private AccountRepository repository = AccountRepositoryImpl.getInstance();
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
        repository.update(this.accountId, this);
    }

    public int getAccountId() {
        return accountId;
    }
}
