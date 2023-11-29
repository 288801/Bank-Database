package operations;
 
import exceptions.BankAccountNotFoundException;

import java.util.Date;

// Добавление суммы на счет
public class PutOperation extends OperationImpl {

    public PutOperation(int id, int sum, int accountId, Date date) throws BankAccountNotFoundException {
        operationId = id;
        this.sum = sum;
        account = db.getAccountById(accountId);
        this.date = date;
    }
 
    @Override
    public void doOperation() {
        account.setBalance(account.getBalance()+sum);
        operationDatabaseService.addOperation(this);
    }
 
    @Override
    public String toString() {
        return formatter.format(date) +" adding " + sum + " to account";
    }

    public int getId(){
        return account.getAccountId();
    }
}
