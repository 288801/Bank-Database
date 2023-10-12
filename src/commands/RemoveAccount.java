package commands;

import services.DatabaseService;

public class RemoveAccount implements Command{

    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            int id = Integer.parseInt(params[0]);
            db.removeAccountById(id);
            return ("Operation completed");
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
