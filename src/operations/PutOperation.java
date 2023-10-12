package operations;
 
import exceptions.BankAccountNotFoundException;

// Добавление суммы на счет
public class PutOperation extends OperationImpl {

    public PutOperation(int sum, int id) throws BankAccountNotFoundException {
        this.sum = sum;
        account = db.getAccountById(id);
    }
 
    @Override
    public void doOperation() {
        account.setBalance(account.getBalance()+sum);
        account.addOperation(this);
    }
 
    @Override
    public String toString() {
        return formatter.format(date) +" adding " + sum + " to account";
    }
}
