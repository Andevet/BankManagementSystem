package main;

import interfaces.ManagementFees;
import interfaces.Profitable;

import java.util.*;

public class ReportManager {
    private AccountManager accountManager;

    public ReportManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }
    public  void printManagerNames() {
		for (Account acc : accountManager.getAccounts()){
			if (acc != null){
				System.out.println("the manager name is: " + acc.getManagerName());

			}
		}
	}
    public String printAllAccountData() {
        Account[] accounts = accountManager.getAccounts();
        int allAccountCounter = accountManager.getAllAccountCounter();
		Arrays.sort(accounts, 0 ,allAccountCounter,new CompareAccountByID());
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < allAccountCounter; i++) {
			sb.append((i + 1) + ") ").append(accounts[i].getClass().getSimpleName() + ": \n" + accounts[i].toString());
		}
		return sb.toString();
	}

    public String showAllProfitAccount() {
        Account[] accounts = accountManager.getAccounts();
        int allAccountCounter = accountManager.getAllAccountCounter();
		Arrays.sort(accounts, 0 , allAccountCounter, new CompareAccountByProfit());
		StringBuilder sb = new StringBuilder();
		int temp = 0;
		for (int i = 0; i < allAccountCounter ; i++) {
			if((accounts[i] instanceof Profitable)) {
				sb.append((temp + 1)).append(") ").append(accounts[i].toString()).append("\n -> This Account make ");
				sb.append(((Profitable) accounts[i]).profitCalculator()).append(" Profit to the bank ------------------ \n\n");
				temp++;
			}

		}
		return sb.toString();
	}

    public String printRgularToString() {
        Account[] accounts = accountManager.getAccounts();
        int allAccountCounter = accountManager.getAllAccountCounter();
        int regularCheckingAccountCounter = accountManager.getRegularCheckingAccountCounter();
		StringBuilder sb = new StringBuilder();
		sb.append("There are ").append(regularCheckingAccountCounter).append(" regular checking account in the system:\n");
		Arrays.sort(accounts, 0 ,allAccountCounter,new CompareAccountByID());
		for (int i = 0,j = 0 ; i < allAccountCounter; i++) {
			if (accounts[i] instanceof RegularCheckingAccount) {
				sb.append(j + 1).append(") -> ").append(accounts[i].toString());
				j++;
			}
		}
		return sb.toString();
	}

	public String printBusinessToString() {
        Account[] accounts = accountManager.getAccounts();
        int allAccountCounter = accountManager.getAllAccountCounter();
        int businessCheckingAccountCounter = accountManager.getBusinessCheckingAccountCounter();
		StringBuilder sb = new StringBuilder();
		sb.append("There are ").append(businessCheckingAccountCounter).append(" business checking account in the system:\n");
		Arrays.sort(accounts, 0 ,allAccountCounter,new CompareAccountByID());
		for (int i = 0 ; i < allAccountCounter; i++) {
			if (accounts[i] instanceof BusinessCheckingAccount) {
				sb.append(i + 1).append(") -> ").append(accounts[i].toString());
			}
		}
		return sb.toString();
	}

	public String printMotrgageToString() {
        Account[] accounts = accountManager.getAccounts();
        int allAccountCounter = accountManager.getAllAccountCounter();
        int mortgageAccountCounter = accountManager.getMortgageAccountCounter();
		StringBuilder sb = new StringBuilder();
		sb.append("There are ").append(mortgageAccountCounter).append(" mortgage account in the system:\n");
		Arrays.sort(accounts, 0 ,allAccountCounter,new CompareAccountByID());
		for (int i = 0 ; i < allAccountCounter; i++) {
			if (accounts[i] instanceof MortgageAccount) {
				sb.append(i + 1).append(") -> ").append(accounts[i].toString());
			}
		}
		return sb.toString();
	}

	public String printSavingToString() {
        Account[] accounts = accountManager.getAccounts();
        int allAccountCounter = accountManager.getAllAccountCounter();
        int savingsAccountCounter = accountManager.getSavingsAccountCounter();
		StringBuilder sb = new StringBuilder();
		sb.append("There are ").append(savingsAccountCounter).append(" savings account in the system:\n");
		Arrays.sort(accounts, 0 ,allAccountCounter,new CompareAccountByID());
		for (int i = 0 ; i < allAccountCounter; i++) {
			if (accounts[i] instanceof SavingsAccount) {
				sb.append(i + 1).append(") -> ").append(accounts[i].toString());
			}
		}
		return sb.toString();
	}

    public  void printManagementFees(){
		double ceoAnnualBonus = 0;
		for (Account acc: accountManager.getAccounts()){
			if (acc instanceof ManagementFees){
				System.out.println( "for " + acc.getClass().getSimpleName() + " Management fees is : " + ((ManagementFees) acc).fees() );
				ceoAnnualBonus += ((ManagementFees) acc).fees();
			}
		}
		System.out.println("CEO's annual bonus is : " + ceoAnnualBonus);
	}

	public String toStringProfit(int index) {
		StringBuilder sb = new StringBuilder();
        Account[] accounts = accountManager.getAccounts();
		if (accounts[index] instanceof Profitable){
			sb.append("The profit from this ").append(accounts[index].getClass().getSimpleName()).append(" is: ").append(((Profitable) accounts[index]).profitCalculator());
		    sb.append(" NIS.\n");
		}

		return sb.toString();
	}

	public void printManagerNamesWithoutDuplication() {
		 // שימוש במבנה Set לשמירה ללא כפילויות
        Set<String> uniqueNames = new LinkedHashSet<>();
        Map<String, Integer> nameCounts = new LinkedHashMap<>();

        for (Account acc : accountManager.getAccounts()) {
			if (acc != null){
				String lowerCaseName = acc.getManagerName().toLowerCase();
				uniqueNames.add(lowerCaseName);
				nameCounts.put(lowerCaseName, nameCounts.getOrDefault(lowerCaseName, 0) + 1);
			}

        }

        // הדפסת התוצאה
        for (String name : uniqueNames) {
            System.out.println(name + " .......... " + nameCounts.get(name));
        }




	}

}
