package operations;
 
import db.Operations;
import exceptions.BankAccountNotFoundException;
import exceptions.DontHaveEnoughMoneyException;

// Снятие суммы со счета
public class GetOperation extends OperationImpl {

    public GetOperation(int id, int sum, int accountId) throws BankAccountNotFoundException {
        operationId = id;
        this.sum = sum;
        account = db.getAccountById(accountId);
    }
 
    @Override
    public void doOperation() throws DontHaveEnoughMoneyException {
        if(sum > account.getBalance()){
            throw new DontHaveEnoughMoneyException();
        }
        account.setBalance(account.getBalance()-sum);
        Operations.getInstance().add(this);
    }
 
    @Override
    public String toString() {
        return formatter.format(date) + " withdrawal " + sum + " from the account";
    }

    public int getId(){
        return account.getAccountId();
    }
}
