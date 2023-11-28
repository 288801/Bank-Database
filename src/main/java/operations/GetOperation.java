package operations;
 
import exceptions.BankAccountNotFoundException;
import exceptions.DontHaveEnoughMoneyException;

import java.util.Date;

// Снятие суммы со счета
public class GetOperation extends OperationImpl {

    public GetOperation(int id, int sum, int accountId, Date date) throws BankAccountNotFoundException {
        operationId = id;
        this.sum = sum;
        account = db.getAccountById(accountId);
        this.date = date;
    }
 
    @Override
    public void doOperation() throws DontHaveEnoughMoneyException {
        if(sum > account.getBalance()){
            throw new DontHaveEnoughMoneyException();
        }
        account.setBalance(account.getBalance()-sum);
        operationDatabaseService.addOperation(this);
    }
 
    @Override
    public String toString() {
        return formatter.format(date) + " withdrawal " + sum + " from the account";
    }

    public int getId(){
        return account.getAccountId();
    }
}
