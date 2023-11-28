package operations;
 
import db.Operations;
import exceptions.BankAccountNotFoundException;

// Добавление суммы на счет
public class PutOperation extends OperationImpl {

    public PutOperation(int id, int sum, int accountId) throws BankAccountNotFoundException {
        operationId = id;
        this.sum = sum;
        account = db.getAccountById(accountId);
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
