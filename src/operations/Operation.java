package operations;

import exceptions.DontHaveEnoughMoneyException;

public interface Operation {

    public void doOperation() throws DontHaveEnoughMoneyException;

    public int getId();

}
