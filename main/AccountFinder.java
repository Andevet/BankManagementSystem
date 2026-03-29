package main;

public class AccountFinder {

    private AccountManager accountManager;

    public AccountFinder(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public Account findAccountByNumber(int accountNumber) {
        for (Account account : accountManager.getAccounts()) {
            if (account != null && account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public boolean isSavingsAccount(int accountNumber) {
        Account account = findAccountByNumber(accountNumber);
        return account instanceof SavingsAccount;
    }
}
