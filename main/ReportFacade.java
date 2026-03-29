package main;

import static main.UserInput.s;

public class ReportFacade {
    private final Bank bank;

    public ReportFacade() {
        this.bank = Bank.getInstance();
    }

    public void printAllAccounts() {
        System.out.println("====== All accounts in the System ======\n" + bank.getReportManager().printAllAccountData());
    }

    public  void showSpecificDataKind() {
		int choice;
		while (true) {
			try {
				System.out.println("For all regular checking account data press 1\nFor all business checking account data press 2\nFor all Mortgage account data press 3\nFor all saving account data press 4/n ");
				choice = Integer.parseInt(s.nextLine());
				switch (choice) {
					case 1 -> System.out.println(bank.getReportManager().printRgularToString());
					case 2 -> System.out.println(bank.getReportManager().printBusinessToString());
					case 3 -> System.out.println(bank.getReportManager().printMotrgageToString());
					case 4 -> System.out.println(bank.getReportManager().printSavingToString());
					default -> throw new NumberFormatException();
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("the number must be integer number from (1 or 2 or 3 or 4)");
			}
		}
	}

    public void printProfitAccounts() {
        System.out.println("====== All Profit accounts in the System ======\n" + bank.getReportManager().showAllProfitAccount());
    }

    public void printAllManagementFees() {
        bank.getReportManager().printManagementFees();
    }

    public void printAllManagers() {
        bank.getReportManager().printManagerNames();
    }

    public void printUniqueManagers() {
        bank.getReportManager().printManagerNamesWithoutDuplication();
    }

    public void countManagerOccurrences() {
        bank.getAccountManager().counterManagerNames();
    }
}
