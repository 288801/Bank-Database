package exceptions;

public class DontHaveEnoughMoneyException extends Exception{
    public DontHaveEnoughMoneyException() {
        super("You don't have enough money to do this operation!");
    }
}
