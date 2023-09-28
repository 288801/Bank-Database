package operations;
 
import account.BankAccount;
import db.Database;
import exceptions.DontHaveEnoughMoneyException;
 
import java.text.SimpleDateFormat;
import java.util.Date;
 
public abstract class Operation {
    protected Database db = Database.getInstance();
    protected SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    protected Date date = new Date(System.currentTimeMillis());
    protected BankAccount account;
 
    public abstract void doOperation() throws DontHaveEnoughMoneyException;
}
