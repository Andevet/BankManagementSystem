package main;

public class AccountCreationResult {
    private  Account account;
    private  int accountNumber;

    public AccountCreationResult(Account account, int accountNumber) {
        this.account = account;
        this.accountNumber = accountNumber;
    }

    public Account getAccount() {
        return account;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
