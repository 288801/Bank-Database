package operations;
 
import exceptions.BankAccountNotFoundException;
import exceptions.DontHaveEnoughMoneyException;

// Снятие суммы со счета
public class GetOperation extends OperationImpl {

    public GetOperation(int sum, int id) throws BankAccountNotFoundException {
        this.sum = sum;
        account = db.getAccountById(id);
    }
 
    @Override
    public void doOperation() throws DontHaveEnoughMoneyException {
        if(sum > account.getBalance()){
            throw new DontHaveEnoughMoneyException();
        }
        account.setBalance(account.getBalance()-sum);
        account.addOperation(this);
    }
 
    @Override
    public String toString() {
        return formatter.format(date) + " withdrawal " + sum + " from the account";
    }
}
