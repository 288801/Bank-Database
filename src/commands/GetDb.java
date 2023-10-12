package commands;

import services.DatabaseService;

public class GetDb implements Command{

    DatabaseService db = DatabaseService.getInstance();

    @Override
    public String execute(String email, String[] params) {
        try {
            return db.getDatabase();
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
