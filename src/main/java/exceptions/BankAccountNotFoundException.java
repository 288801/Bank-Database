package exceptions;

import models.BankAccount;

public class BankAccountNotFoundException  extends Exception{
    public BankAccountNotFoundException() {
        super("Account not found");
    }
}
