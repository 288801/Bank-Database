package repositories;

import models.BankAccount;

import java.util.List;

public interface AccountRepository extends Repository<BankAccount, Integer> {
    public List<BankAccount> getUserAccounts(String email);
}
