package main;

import bankException.AccountNotFoundException;
import bankException.CheckedException;
import bankException.GeneralException;
import interfaces.Profitable;

import static main.UserInput.s;

public class ProfitCalculator {
    private AccountManager accountManager;

    public ProfitCalculator(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public  void checkProfitVIP() {
		try {
			System.out.println("Enter the account number you want to check the profit lost: ");
			int accountNumber = Integer.parseInt(s.nextLine());
			if (accountNumber > 0 ){
				checkProfitForVip(accountNumber);
			}
			 else{
				 throw  new NumberFormatException();
			 }
        } catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid account number.");
        } catch (GeneralException e) {
            throw new RuntimeException(e);
		}
    }

    public void checkProfitForVip(int accountNumber) throws GeneralException {
		try {
			Account targetAccount = null;
			for (Account account : accountManager.getAccounts()) {
				if (account != null && account.getAccountNumber() == accountNumber) {
					targetAccount = account;
					break;
				}
			}

			if (targetAccount == null) {
				throw new AccountNotFoundException("Account with number " + accountNumber + " not found.");
			}
		    // בדיקת האם החשבון הוא עסקי ומממש את המתודה checkProfitVIP
		    if (targetAccount instanceof BusinessCheckingAccount) {
				BusinessCheckingAccount tempBusinessAccount = (BusinessCheckingAccount) targetAccount;
				int profitLost = checkProfitVip(tempBusinessAccount);
				System.out.println("The profit the bank loses for this  account is: " + profitLost);
			}
			else {
				System.out.println("Account number " + accountNumber + " is not a business account.");
			}
		    }catch (AccountNotFoundException e) {
			    System.out.println(e.getMessage());
		    } catch (Exception e) {
			    System.out.println("An unexpected error occurred: " + e.getMessage());
		    }
	}

    public int checkProfitVip(BusinessCheckingAccount businessAccount) throws GeneralException {
		if (!businessAccount.isVip()) {
			System.out.println("The account is not VIP.");
			return 0; // אם החשבון אינו VIP, אין רווח לוותר עליו
        }
		// יצירת עותק של החשבון
	    BusinessCheckingAccount copy = new BusinessCheckingAccount(businessAccount.getManagerName(),businessAccount.getAccountNumber(), true,businessAccount.getCredit(), businessAccount.getBusinessRevenue());

        // הוספת כל הלקוחות לעותק
       for (Client client : businessAccount.getClients()) {
		   if (client != null){
			   copy.addClient(new Client(client.getName(), client.getClientRank()));
		   }
	   }
       // שינוי הדרגות של כל הלקוחות ל-0
	   for (Client client : copy.getClients()) {
		   if (client != null){
			   client.setClientRank(0);
		   }
	   }
	   // חישוב הרווח המקורי
       int originalProfit = copy.profitCalculator();
	   System.out.println("the Account is vip. ");
        return originalProfit; // הרווח שהבנק מוותר עליו
	}

    public String calculateAllAccountsProfit() {
		StringBuilder sb = new StringBuilder();
		int bankProfit = 0,  j = 0;
        int allAccountCounter = accountManager.getAllAccountCounter();
        Account[] accounts = accountManager.getAccounts();
		for(int i = 0; i < allAccountCounter ; i++) {
			if((accounts[i] instanceof Profitable) && i != allAccountCounter - 1) {
				sb.append(j + 1).append(") ").append(accounts[i].getClass().getSimpleName()).append(" there profit to the bank from this account is - ");
				sb.append(((Profitable) accounts[i]).profitCalculator()).append(".\n");
				bankProfit += ((Profitable) accounts[i]).profitCalculator();
				sb.append("----------------------------------\n").append("  -> The bank profit with this account is: ").append(bankProfit);
				sb.append(" NIS.\n----------------------------------\n");
				j++;
			}
			else {
				j = i;
			}
		}
		sb.append("The profit from all the account's they in the system is: ").append(bankProfit).append("NIS.\n________________________________\n");
		return sb.toString();
	}

    public  String mostProfit() {
        int maxIndex = 0;
        int maxProfit = 0;
        int allAccountCounter = accountManager.getAllAccountCounter();
        Account[] accounts = accountManager.getAccounts();
        for (int i = 0; i < allAccountCounter; i++) {
            if (accounts[i] instanceof CheckingAccount && accounts[i] instanceof Profitable) {
                if (maxProfit < ((Profitable) accounts[i]).profitCalculator()) {
                    maxProfit = ((Profitable) accounts[i]).profitCalculator();
                    maxIndex = i;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(accounts[maxIndex].toString());

        return sb.toString();
    }

    public  void annualProfit(Bank bank) {
		int accountNumber;
		int index;
		while(true) {
			try {
				System.out.println("Enter the account number you want to check is annual profit\n");
				accountNumber = Integer.parseInt(s.nextLine());
				if (accountNumber <= 0 ) {
					throw new NumberFormatException();
				}
				   index = accountManager.findAccountIndex(accountNumber);
				if(index == -1) {
					throw new CheckedException(accountNumber);
				}
				if(bank.getCalculationManager().getAccountFinder().isSavingsAccount(index)) {
					throw new GeneralException("The account is a saving account he didn't profit to the bank\nTry another number account");
				}
				break;
			}catch(NumberFormatException e) {
				System.out.println("wrong input the account number must be a integer number above zero");
			}catch(CheckedException e) {
				System.out.println("this account number is not in the SYSTEM\nTry another number");
			}catch (GeneralException e) {
				System.out.println(e);
			}
		}
		System.out.println(bank.getReportManager().toStringProfit(index));
	}


}
