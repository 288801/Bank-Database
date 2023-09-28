package operations;
 
import exceptions.DontHaveEnoughMoneyException;
import exceptions.UserNotFoundException;
 
// Снятие суммы со счета
public class GetOperation extends Operation{
    private int sum;
 
    public GetOperation(int sum, int id) throws UserNotFoundException {
        this.sum = sum;
        account = db.getUserById(id);
    }
 
    public GetOperation(int sum, String name, String surname) throws UserNotFoundException {
        this.sum = sum;
        account = db.getUserByName(name, surname);
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
