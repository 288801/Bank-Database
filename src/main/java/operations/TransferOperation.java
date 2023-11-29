package operations;
 
import exceptions.BankAccountNotFoundException;
import exceptions.DontHaveEnoughMoneyException;

import java.util.Date;

// Передача суммы с одного счета на другой
public class TransferOperation extends OperationImpl {

    public TransferOperation(int id, int sum, int senderId, int destinationId, Date date) throws BankAccountNotFoundException {
        operationId = id;
        this.sum = sum;
        account = db.getAccountById(senderId);
        destination = db.getAccountById(destinationId);
        this.date = date;
    }

    @Override
    public void doOperation() throws DontHaveEnoughMoneyException {
        if(sum > account.getBalance()){
            throw new DontHaveEnoughMoneyException();
        }
        account.setBalance(account.getBalance()-sum);
        destination.setBalance(destination.getBalance()+sum);
        operationDatabaseService.addOperation(this);
    }
 
    @Override
    public String toString() {
        return formatter.format(date) + " transfer " + sum + " from " + account.getOwnerName() + " " +
                account.getOwnerSurname() + " to " +
                destination.getOwnerName() + " " +
                destination.getOwnerSurname();
    }

    public int getId(){
        return account.getAccountId();
    }
}
