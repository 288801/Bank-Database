import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private int accountId;
    private String ownerName;
    private String ownerSurname;
    private int balance;
    private List<Operation> operationHistory = new ArrayList<>();

    public BankAccount(int accountId, String ownerName, String ownerSurname, int balance) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.balance = balance;
    }

}
