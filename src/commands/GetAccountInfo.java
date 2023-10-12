package commands;

import services.DatabaseService;

public class GetAccountInfo implements Command{

    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            return db.getAccountInfo(email, Integer.parseInt(params[0]));
        } catch (Exception e) {
            return (e.getMessage());
        }    }
}
