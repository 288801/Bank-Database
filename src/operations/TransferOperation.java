package operations;
 
import exceptions.BankAccountNotFoundException;
import models.BankAccount;
import exceptions.DontHaveEnoughMoneyException;
import exceptions.UserNotFoundException;
 
// Передача суммы с одного счета на другой
public class TransferOperation extends Operation{
    private BankAccount destination;
 
    public TransferOperation(int sum, int senderId, int destinationId) throws BankAccountNotFoundException {
        this.sum = sum;
        account = db.getAccountById(senderId);
        destination = db.getAccountById(destinationId);
    }

    @Override
    public void doOperation() throws DontHaveEnoughMoneyException {
        if(sum > account.getBalance()){
            throw new DontHaveEnoughMoneyException();
        }
        account.setBalance(account.getBalance()-sum);
        destination.setBalance(destination.getBalance()+sum);
        account.addOperation(this);
        destination.addOperation(this);
    }
 
    @Override
    public String toString() {
        return formatter.format(date) + " transfer " + sum + " from " + account.getOwnerName() + " " +
                account.getOwnerSurname() + " to " +
                destination.getOwnerName() + " " +
                destination.getOwnerSurname();
    }
}
