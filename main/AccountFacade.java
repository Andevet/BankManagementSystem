package main;

import bankException.NameException;

public class AccountFacade {
    private final Bank bank;

    public AccountFacade() {
        this.bank = Bank.getInstance();
    }

    public void initializeBank() {
        bank.addObserver(new Action1());
        bank.addObserver(new Action2());
    }

    public void uploadAutoData() throws NameException {
        bank.uploadAutoData(bank);

    }

    public void createAccount() {
        try {
            bank.getAccountManager().newAccount(bank);
        } catch (Exception e) {
            System.out.println("Failed to create account: " + e.getMessage());
        }
    }

    public void addClientToAccount() {
        try {
            if (ClientHelper.addNewClient()) {
                System.out.println("Client added successfully.");
            } else {
                System.out.println("Failed to add client.");
            }
        } catch (Exception e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
    }
}
