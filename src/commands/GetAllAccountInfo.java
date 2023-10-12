package commands;

import services.DatabaseService;

public class GetAllAccountInfo implements Command{

    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            return db.getUserAccountsInfo(email);
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
