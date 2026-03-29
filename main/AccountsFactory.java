package main;

import bankEnum.AccountType;
import bankException.AutoDataExcption;
import bankException.GeneralException;
import bankException.NameException;

import java.util.List;

import static main.UserInput.s;

public class AccountsFactory{

	public static AccountCreationResult createAccount(AccountType type) throws GeneralException {
        String managerName = UserInput.getValidManagerName();
        boolean auto = autoNumber();
        int accountNumber = UserInput.getAccountNumber(auto);
        Account account;
        switch (type) {
            case REGULARCHECKINGACCOUNT:
                int credit = UserInput.getCreditInput();
                 account =  new RegularCheckingAccount(managerName, accountNumber, auto, credit);
                 break;
            case BUSINESSCHECKINGACCOUNT:
                credit = UserInput.getCreditInput();
                int businessRevenue = UserInput.getBusinessRevenue();
                account =  new BusinessCheckingAccount(managerName, accountNumber, auto,credit , businessRevenue);
                break;
            case MORTGAGEACCOUNT:
                int mortgageAmount = UserInput.getMortgageDetails("Mortgage Amount");
                int yearsForMortgage = UserInput.getYears("Mortgage Term");
                int monthlyPayment = UserInput.getYears("Monthly payment");
                account = new MortgageAccount(managerName, accountNumber, auto, mortgageAmount, yearsForMortgage,monthlyPayment);
                break;
            case SAVINGACCOUNT:
                int depositAmount = UserInput.getDepositAmount();
                int savingYears = UserInput.getYears("Saving Term");
                account = new SavingsAccount(managerName, accountNumber, auto, depositAmount, savingYears);
                break;
            default:
                throw new IllegalArgumentException("Unsupported account type: " + type);
        }
        return new AccountCreationResult(account, accountNumber);
    }
	public static List<String> createManagerNamesList(Bank bank) {
        return AccountListCreator.createManagerNamesList(bank);
    }


	public void newRandomAccount(int index,Bank bank,int indexAccount) throws NameException {
		AccountType accountType = AccountType.values()[index];
		switch (accountType) {
			case REGULARCHECKINGACCOUNT:
				createRegularAccount(bank, indexAccount);
				break;
            case BUSINESSCHECKINGACCOUNT:
				createBusinessAccount(bank, indexAccount);
				break;
            case MORTGAGEACCOUNT:
				createMortgageAccount(bank, indexAccount);
				break;
            default:
				createSavingsAccount(bank, indexAccount);
				break;
		}
	}

	private void createBusinessAccount(Bank bank, int indexAccount) throws NameException {
		String managerName = RandomDataProvider.getRandomManagerName();
		int creditValue = RandomDataProvider.getRandomCredit();
		int businessRevenue = RandomDataProvider.getRandomBusinessRevenue();
		try {
				bank.getAccountManager().addAccount(managerName, 0, AccountType.BUSINESSCHECKINGACCOUNT, true, creditValue,businessRevenue);
				ClientHelper.addRandomClientsToAccount(bank, indexAccount); // שימוש במחלקת העזר
		} catch (GeneralException e) {
			System.out.println("Error creating Business Account: " + e.getMessage());
		}
	}

	private void createRegularAccount(Bank bank, int indexAccount) throws NameException {
		String managerName = RandomDataProvider.getRandomManagerName();
		int creditValue = RandomDataProvider.getRandomCredit();
		try {
			bank.getAccountManager().addAccount(managerName, 0, AccountType.REGULARCHECKINGACCOUNT, true, creditValue);
			ClientHelper.addRandomClientsToAccount(bank, indexAccount); // שימוש במחלקת העזר
		} catch (GeneralException e) {
			System.out.println("Error creating Regular Account: " + e.getMessage());
		}
	}
	private void createMortgageAccount(Bank bank, int indexAccount) throws NameException {
		String managerName = RandomDataProvider.getRandomManagerName();
		int years = RandomDataProvider.getRandomYearsForMortgage();
		int mortgageAmount = RandomDataProvider.getRandomMortgageAmount();
		int monthlyPayment = RandomDataProvider.getRandomMonthlyPayMent();
		try {
			bank.getAccountManager().addAccount(managerName, 0, true, years,mortgageAmount,monthlyPayment);
			ClientHelper.addRandomClientsToAccount(bank, indexAccount); // שימוש במחלקת העזר
		} catch (GeneralException e) {
			System.out.println("Error creating Mortgage Account: " + e.getMessage());
		}
	}

	private void createSavingsAccount(Bank bank, int indexAccount) throws NameException {
		String managerName = RandomDataProvider.getRandomManagerName();
		int years = RandomDataProvider.getRandomYearsForMortgage();
		int depositAmount = RandomDataProvider.getRandomDepositAmount();
		try {
			bank.getAccountManager().addAccount(managerName, 0, true, years,depositAmount);
			ClientHelper.addRandomClientsToAccount(bank, indexAccount); // שימוש במחלקת העזר
		} catch (GeneralException e) {
			System.out.println("Error creating Savings Account: " + e.getMessage());
		}
	}


	public static boolean checkLetter(String input) {
		if( (input.length() != 1) && (input.charAt(0) != 'e' && input.charAt(0) != 'E')  ) {
			return false;
		}
		else {
			return true;
		}
	}

	public static boolean autoNumber() {
        int userInput;
        while (true) {
            try {
                System.out.println("1.) press 1 for automatic account number\n2.) press - 2 to enter the account number by your selfe ");
                userInput = Integer.parseInt(s.nextLine());
                if (userInput != 1 && userInput != 2) {
                    throw new AutoDataExcption();
                }
                return userInput == 1 ? true : false;
            } catch (NumberFormatException | AutoDataExcption e) {
                System.out.println("you must enter 1 or 2");
            }
        }
    }



}
