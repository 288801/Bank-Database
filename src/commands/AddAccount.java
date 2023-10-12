package commands;

import services.DatabaseService;

public class AddAccount implements Command{

    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            db.createAccount(email, Integer.parseInt(params[0]));
            return ("Operation completed");
        } catch (Exception e) {
            return ("The entered data is incorrect, please use the format '--add balance'");
        }
    }
}
