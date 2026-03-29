package main;

import java.util.*;

import bankEnum.AccountType;
import bankException.NameException;
import bankManagement.BankCalculate;
import bankManagement.BankObserver;
import interfaces.Observer;


public class Bank extends BankCalculate
{
	private static Bank instance; // משתנה סטטי לאחסון המופע היחיד
	private ArrayList<String> arrayList;
	private BankObserver bankObserver = new BankObserver();
	private ReportManager reportManager;
    private AccountManager accountManager;
	private CalculationManager calculationManager;
	private static final int MIN_ACCOUNTS = 4;
    private static final int MAX_ACCOUNT = 3;

	private Bank() {
		this.accountManager = new AccountManager();
        this.reportManager = new ReportManager(accountManager);
        this.calculationManager = new CalculationManager(accountManager);
        this.bankObserver = new BankObserver();
	}

	// מתודה ציבורית סטטית לגישה למופע
    public static Bank getInstance() {
        if (instance == null) {
			instance = new Bank();
        }
        return instance;
    }

	public void addObserver(Observer observer) {
		bankObserver.attach(observer);

    }
	public AccountManager getAccountManager() {
        return accountManager;
    }

    public ReportManager getReportManager() {
        return reportManager;
    }

    public CalculationManager getCalculationManager() {
        return calculationManager;
    }
	public BankObserver getBankObserver() {
        return bankObserver;
    }

	public Iterator<String> iterator() {
        return new CustomIterator(arrayList,this);
    }
	public ListIterator<String> ListIterator() {
        return new CustomListIterator(arrayList,this);
    }

	public static class CustomIterator implements Iterator<String> {
		final List<String> list;
		int currentIndex;
		final Bank  bank;


        public CustomIterator(List<String> list ,Bank bank) {
            this.list = list;
            this.currentIndex = -1; // Start before the first element
			this.bank = bank;
        }

        public boolean hasNext() {
            return currentIndex < list.size() - 1;
        }

        public String next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more elements.");
			}
			String value = list.get(++currentIndex);
			return value;

        }

        public boolean hasPrevious() {
            return currentIndex > 0;

        }

        public String previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException("No previous elements.");
			}
			String value = list.get(currentIndex--);
			return value;
        }
		@Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported.");
        }


	}

	protected static class CustomListIterator extends CustomIterator implements ListIterator<String> {

        public CustomListIterator(List<String> list,Bank bank) {
            super(list,	bank);
			this.currentIndex = 0;

        }
		@Override
        public boolean hasNext() {
            return currentIndex < list.size();
        }
		@Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

		@Override
		public String next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more elements.");
			}
			String value = list.get(currentIndex++);
			return value;
        }
		@Override
		public String previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException("No previous elements.");
			}
			String value = list.get(--currentIndex);
			return value;
        }



		@Override
		public int nextIndex() {
			return currentIndex;
		}

		@Override
		public int previousIndex() {
			return currentIndex -1;
		}




		@Override
		public void set(String s) {
			throw new UnsupportedOperationException("Set operation is not supported.");
		}

		@Override
		public void add(String s) {
			throw new UnsupportedOperationException("Add operation is not supported.");
		}
	}

	public void notifyIterationEnded(String message) {
        bankObserver.notify(message);
    }

	public void uploadAutoData(Bank bank) throws NumberFormatException, NameException {
	Random r = new Random();

    AccountsFactory newAccountFactory = new AccountsFactory();
    int accountTypeCount = AccountType.values().length;
    int totalAccounts = MIN_ACCOUNTS + r.nextInt(MAX_ACCOUNT); // Generate between 4 and 6 accounts

    // Step 1: Add one account of each type
    for (int i = 0; i < accountTypeCount; i++) {
        try {
            newAccountFactory.newRandomAccount(i, bank, bank.getAccountManager().getNextAccountNumber()-1);
        } catch (Exception e) {
            System.err.println("Failed to create account of type " + AccountType.values()[i] + ": " + e.getMessage());
        }
    }

    // Step 2: Add remaining accounts randomly
    int remainingAccounts = totalAccounts - accountTypeCount;
    while (remainingAccounts > 0) {
        int randomAccountType = r.nextInt(accountTypeCount); // Pick a random account type
        try {
            newAccountFactory.newRandomAccount(randomAccountType, bank, AccountManager.getNextAccountNumber()-1);
        } catch (Exception e) {
            System.err.println("Failed to create random account: " + e.getMessage());
        }
        remainingAccounts--;
    }




	}

	public static void checkString(String nameOfTheManager)throws NameException {
		// בדיקה אם המחרוזת ריקה או מכילה רק רווחים
        if (nameOfTheManager == null || nameOfTheManager.trim().isEmpty()) {
			throw new NameException("The name cannot be empty or contain only spaces!");
		}

		for (int i = 0; i < nameOfTheManager.length(); i++) {
			if(!Character.isLetter(nameOfTheManager.charAt(i))) {
				throw new NameException("The name contains invalid characters: " + nameOfTheManager.charAt(i));
			}
		}
	}


}









