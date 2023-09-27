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

    @Override
    public String toString() {
        return "BankAccount_" + accountId + "{" +
                "ownerName='" + ownerName +
                "', ownerSurname='" + ownerSurname +
                "', balance=" + balance +
                ", operationHistory=" + operationHistory +
                '}';
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Operation> getOperationHistory() {
        return operationHistory;
    }

    public void setOperationHistory(List<Operation> operationHistory) {
        this.operationHistory = operationHistory;
    }
}
