package commands;

import services.DatabaseService;

public class RemoveUser implements Command{

    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            db.removeUserByEmail(params[0]);
            return ("Operation completed");
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
