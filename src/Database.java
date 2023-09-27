import exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<BankAccount> accounts;
    private static Database instance;

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();

        return instance;
    }

    private Database() {
        accounts = new ArrayList<>();
    }

    public void addUser(String name, String surname, int balance){
        int id = accounts.size();
        accounts.add(new BankAccount(id, name, surname, balance));
    }

    public String getUserById(int id) throws UserNotFoundException {
        try {
            return accounts.get(id).toString();
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    public String getUserByName(String name, String surname) throws UserNotFoundException {
        for(BankAccount account : accounts){
            if(account.getOwnerName().equals(name) && account.getOwnerSurname().equals(surname)){
                return account.toString();
            }
        }
        throw new UserNotFoundException();
    }

    public String getDatabase(){
        if(accounts.isEmpty()){
            return "Database is empty";
        }
        String result = "";
        for(BankAccount account : accounts){
            result += account.toString() + "\n";
        }
        return result;
    }

    public void removeById(int id) throws UserNotFoundException {
        try{
            accounts.remove(id);
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    public void removeByName(String name, String surname) throws UserNotFoundException {
        BankAccount user = null;
        for(BankAccount account : accounts){
            if(account.getOwnerName().equals(name) && account.getOwnerSurname().equals(surname)){
                user = account;
            }
        }
        try{
            accounts.remove(user);
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }
}
