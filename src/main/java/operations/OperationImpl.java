package operations;
 
import models.BankAccount;
import exceptions.DontHaveEnoughMoneyException;
import services.AccountDatabaseService;
import services.DatabaseService;
import services.OperationDatabaseService;
import services.UserDatabaseService;

import java.text.SimpleDateFormat;
import java.util.Date;
 
public abstract class OperationImpl implements Operation{

    protected int operationId;
    protected AccountDatabaseService db = AccountDatabaseService.getInstance();
    protected SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    protected Date date = new Date(System.currentTimeMillis());
    protected BankAccount account;
    protected int sum;
    protected OperationDatabaseService operationDatabaseService = OperationDatabaseService.getInstance();

    public abstract void doOperation() throws DontHaveEnoughMoneyException;

    public abstract int getId();
}
