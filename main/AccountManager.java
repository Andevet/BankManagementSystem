package main;

import bankEnum.AccountType;
import bankException.*;

import java.util.*;

import static main.Bank.checkString;
import static main.UserInput.s;

public class AccountManager {
    private static Account[] accounts;
    private AccountType type;
    private static int nextAccountNumber = 1;
    private static int allAccountCounter = 0;
    private int regularCheckingAccountCounter;
	private int businessCheckingAccountCounter;
	private int mortgageAccountCounter;
	private int savingsAccountCounter;
	private ArrayList<String> arrayList;

    public AccountManager() {
        this.accounts = new Account[2];
        this.regularCheckingAccountCounter = 0;
		this.businessCheckingAccountCounter = 0;
		this.mortgageAccountCounter = 0;
		this.savingsAccountCounter = 0;
        this.allAccountCounter = 0;

    }

	public void setItems(List<String> arrayList) {
        this.arrayList = new ArrayList<>(arrayList);
    }

    public void addAccount(String managerName,int numberAccount,AccountType type, boolean auto,int credit)throws GeneralException
	{
		if(allAccountCounter == accounts.length)
		{
			resizeArray();
		}
			RegularCheckingAccount newAccount = new RegularCheckingAccount(managerName,numberAccount,auto,credit);
			accounts[this.allAccountCounter++] = newAccount;
			this.regularCheckingAccountCounter++;
        this.type = type;
	}


	public void addAccount(Account account, AccountType accountType)throws GeneralException
	{
		if(allAccountCounter == accounts.length)
		{
			resizeArray();
		}
		 switch (accountType) {
			case REGULARCHECKINGACCOUNT:
                accounts[this.allAccountCounter++] = account;
				this.regularCheckingAccountCounter++;
                this.type = accountType;
				break;
			case BUSINESSCHECKINGACCOUNT:
                accounts[this.allAccountCounter++] = account;
				this.businessCheckingAccountCounter++;
                this.type = accountType;
				break;
			case MORTGAGEACCOUNT:
                accounts[this.allAccountCounter++] = account;
				this.mortgageAccountCounter++;
				this.type = accountType;
				break;
            case SAVINGACCOUNT:
                accounts[this.allAccountCounter++] = account;
				this.savingsAccountCounter++;
				break;
			default:
                throw new IllegalArgumentException("Unsupported account type: " + type);
        }
	}
	public void addAccount(String nameOfTheManager, int userInputForTheAccountNumber, AccountType personalAccount,
			boolean auto, int credit, int businessRevenue) throws GeneralException{
		if(personalAccount != null && personalAccount == AccountType.BUSINESSCHECKINGACCOUNT)
		{
			if(allAccountCounter == accounts.length)
		{
			resizeArray();
		}
			BusinessCheckingAccount newAccount = new BusinessCheckingAccount(nameOfTheManager,userInputForTheAccountNumber,auto,credit,businessRevenue);
			accounts[this.allAccountCounter++] = newAccount;
			this.businessCheckingAccountCounter++;
            this.type = personalAccount;
		}

	}
	/*for mortgage account*/
	public void addAccount(String managerName,int numberAccount, boolean auto,int year,int originalMortgageAmount,float monthlyPayment) throws GeneralException
	{
		if(allAccountCounter == accounts.length) {
			resizeArray();
		}
		MortgageAccount newAccount = new MortgageAccount(managerName,numberAccount,auto,originalMortgageAmount, year,monthlyPayment);
		accounts[this.allAccountCounter++] = newAccount;
		this.mortgageAccountCounter++;
	}
	/*for saving account*/
	public void addAccount(String managerName,int numberAccount, boolean auto, int year,int depositAmount)throws GeneralException {
		if(allAccountCounter == accounts.length) {
			resizeArray();
		}
		SavingsAccount newAccount = new SavingsAccount(managerName,numberAccount,auto,year, depositAmount);
		accounts[this.allAccountCounter++] = newAccount;
		this.savingsAccountCounter++;
	}

    public void addClientData(String nameOfTheClient, int clientRank, int accountIndex) throws NameException {
		if (accounts[accountIndex] != null) {
			if(!checkClientName(accountIndex,nameOfTheClient)) {
				accounts[accountIndex].newClient( nameOfTheClient, clientRank);
			}

		}

		else {
			    System.out.println("Account at index " + accountIndex + " is not initialized.");
			    throw new NameException("the name is already in the accounts ");
		    }

    }

    public static boolean checkClientName(int accountIndex, String name) {
		for (int i = 0; i < allAccountCounter; i++) {
			if (accounts[i] != null){
				for(int j = 0 ; j < accounts[i].getClients().length;j++ ) {
					if (accounts[i].getClients()[j] != null && name.equals(accounts[i].getClients()[j].getName())){
							return true;
					}
				}
			}
		}
		return false;
	}

    public static boolean addNewClientData(String nameOfTheClient, int clientRank,int accountNumber) {
		 Account account = findAccountByNumber(accountNumber); // חיפוש החשבון לפי מספר
		 if (account == null) {
			 System.out.println("Account with number " + accountNumber + " not found.");
			 return false;
		 }


		else {
			// חיפוש החשבון במערכת לפי מספר חשבון
			 if(!checkClientName(accountNumber-1,nameOfTheClient)) { // הוספת הלקוח לחשבון
               account.addClient(new Client(nameOfTheClient, clientRank));
			   return true;
			 }
			else {
				System.out.println("Client not added to account number " + accountNumber + " because there is a client with this name ");
				return false;
			}

		}

	}

    private static Account findAccountByNumber(int accountNumber) {
	    for (Account account : accounts) {
			if (account != null && account.getAccountNumber() == accountNumber) {
				return account;
			}
		}
		return null; // אם החשבון לא נמצא
	}

    public static void checkAccountNumber(int userInputForTheAccountNumber)throws CheckedException {
		for (Account account : accounts) {
			  if (account != null && account.getAccountNumber() == userInputForTheAccountNumber) {
				  throw new CheckedException(userInputForTheAccountNumber);
              }
        }
	}

    public int findAccountIndex(int number) {
		for(int i = 0; i < accounts.length;i++) {
			if(accounts[i] != null && accounts[i].getAccountNumber() == number) {
				return i;
			}
		}
		return -1;
	}

    private void resizeArray() {
        this.accounts = Arrays.copyOf(accounts, accounts.length * 2);
    }

	public int getAllAccountCounter() {
        return allAccountCounter;
    }

    public int getBusinessCheckingAccountCounter() {
        return businessCheckingAccountCounter;
    }

    public int getMortgageAccountCounter() {
        return mortgageAccountCounter;
    }

    public int getSavingsAccountCounter() {
        return savingsAccountCounter;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public int getRegularCheckingAccountCounter() {
        return regularCheckingAccountCounter;
    }


    public Account[] getAccounts() {
        return accounts;
    }

    public static int getNextAccountNumber() {
        return nextAccountNumber++;
    }

    public  void newAccount(Bank bank) {
        while(true) {
			try {
                // Create the account and get the account number
				   AccountType accountType = UserInput.getAccountTypeFromUser();
				   if (accountType == null){
					   break;
				   }
				   AccountCreationResult result = AccountsFactory.createAccount(accountType);
				   bank.getAccountManager().addAccount(result.getAccount(),accountType);
                 // Add clients to the new account
				   ClientHelper.addClientsToAccount(bank,result.getAccountNumber());
				   break;
				}catch(NumberFormatException e) {
					System.out.println(e + "its wrong input\n,Try again");
				} catch (GeneralException e) {
				    throw new RuntimeException(e);
			   }
        }
	}

    public static int addNumberAccount(boolean auto) {
		if(!auto) {
			int number;
			while(true) {
				try {
					System.out.println("enter the number:");
					number = Integer.parseInt(s.nextLine());
					if(number < 0) {
						throw new GeneralException("you must enter number above 0");
					}
					checkAccountNumber(number);
					return number;
					}catch(NumberFormatException e) {
						System.out.println("you need to enter an integer number");
					}
					catch(DuplicationException e) {
						System.out.println(e);
					}
				catch(GeneralException e) {
				System.out.println(e);
				}
			}
		}else{
			 return getNextAccountNumber();
		}

	}

	public void counterManagerNames() {
		System.out.println("please enter a manager name: ");
		String name;
		while(true) {
					try {
							System.out.println("enter the manager name");
							name  = s.nextLine();
							checkString(name);
							break;
						}catch(NameException e) {
							System.out.println(e.getMessage());
						}
		}
		int num = counterManagerNames(name);
		System.out.println("The number of times " + name + " appears in the original array is " + num + ". \n");
	}

	public int counterManagerNames(String name) {
		int num =0;
		for (Account acc : accounts){
			if(acc != null && acc.getManagerName().equalsIgnoreCase(name)){
				num++;

			}
		}
		return num;
	}




}


