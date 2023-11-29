package commands;

public interface Command {

    public String execute(String email, String[] params);
}
