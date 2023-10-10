package operations;
 
import account.BankAccount;
import exceptions.DontHaveEnoughMoneyException;
import exceptions.UserNotFoundException;
 
// Передача суммы с одного счета на другой
public class TransferOperation extends Operation{
    private int sum;
    private BankAccount destination;
 
    public TransferOperation(int sum, int senderId, int destinationId) throws UserNotFoundException {
        this.sum = sum;
        account = db.getUserById(senderId);
        destination = db.getUserById(destinationId);
    }
 
    public TransferOperation(int sum, String senderName, String senderSurname, String destinationName, String destinationSurname) throws UserNotFoundException {
        this.sum = sum;
        account = db.getUserByName(senderName, senderSurname);
        destination = db.getUserByName(destinationName, destinationSurname);
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
