package db;

import exceptions.BankAccountNotFoundException;
import exceptions.UserNotFoundException;

import java.util.List;

public interface Table<T> {

    public List<T> getTable();

    public void add(T obj) throws UserNotFoundException;

    public T getById(String id) throws UserNotFoundException, BankAccountNotFoundException;

    public void removeById(String id) throws UserNotFoundException, BankAccountNotFoundException;

}
