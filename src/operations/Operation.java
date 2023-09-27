package operations;

import account.BankAccount;
import db.Database;
import exceptions.DontHaveEnoughMoneyException;

public abstract class Operation {
    protected Database db = Database.getInstance();
    protected BankAccount account;

    public abstract void doOperation() throws DontHaveEnoughMoneyException;
}
