package commands;

import services.DatabaseService;

public class AddUser implements Command{

    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            db.addUser(params[0], params[1], params[2], params[3]);
            return ("Operation completed");
        } catch (Exception e) {
            return ("The entered data is incorrect, please use the format 'name surname email password'");
        }
    }
}
