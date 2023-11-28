package db;

import exceptions.BankAccountNotFoundException;
import exceptions.UserNotFoundException;
import operations.Operation;
import operations.OperationImpl;

import java.util.ArrayList;
import java.util.List;

public class Operations implements Table<Operation>{

    private List<Operation> operations;
    private static Operations instance;

    public static Operations getInstance() {
        if (instance == null)
            instance = new Operations();

        return instance;
    }

    private Operations() {
        operations = new ArrayList<>();
    }

    @Override
    public List<Operation> getTable() {
        return operations;
    }

    @Override
    public void add(Operation operation) {
        operations.add(operation);
    }

    @Override
    public Operation getById(String id) {
        try {
            return operations.get(Integer.parseInt(id));
        }catch (Exception e){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void removeById(String id) {
        try{
            operations.remove(Integer.parseInt(id));
        }catch (Exception e){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "Operations{" +
                "operations=" + operations +
                '}';
    }

    public int size(){
        return operations.size();
    }
}
