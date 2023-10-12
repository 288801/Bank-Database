package operations;
 
import models.BankAccount;
import exceptions.DontHaveEnoughMoneyException;
import services.DatabaseService;

import java.text.SimpleDateFormat;
import java.util.Date;
 
public abstract class OperationImpl implements Operation{
    protected DatabaseService db = new DatabaseService();
    protected SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    protected Date date = new Date(System.currentTimeMillis());
    protected BankAccount account;
    protected int sum;

    public abstract void doOperation() throws DontHaveEnoughMoneyException;
}
