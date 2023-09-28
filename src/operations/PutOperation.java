package operations;
 
import db.Database;
import exceptions.UserNotFoundException;
 
// Добавление суммы на счет
public class PutOperation extends Operation{
    private int sum;
 
    public PutOperation(int sum, int id) throws UserNotFoundException {
        this.sum = sum;
        account = db.getUserById(id);
    }
 
    public PutOperation(int sum, String name, String surname) throws UserNotFoundException {
        this.sum = sum;
        account = db.getUserByName(name, surname);
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
