package main;



public class CalculationManager {
    private final AccountManager accountManager;
	private ProfitCalculator profitCalculator;
	private AccountFinder accountFinder;
    private ManagerListHandler managerListHandler;

    public CalculationManager(AccountManager accountManager) {
		this.accountManager = accountManager;
        this.profitCalculator = new ProfitCalculator(accountManager);
        this.accountFinder = new AccountFinder(accountManager);
        this.managerListHandler = new ManagerListHandler(accountManager);
    }


    public  void checkProfitVIP() {
		profitCalculator.checkProfitVIP();

    }

    public String calculateAllAccountsProfit() {
		return profitCalculator.calculateAllAccountsProfit();
	}

    public  void annualProfit(Bank bank) {
		profitCalculator.annualProfit(bank);
	}


	public  String mostProfit() {
		return profitCalculator.mostProfit();
	}


	public AccountFinder getAccountFinder() {
		return accountFinder;
	}


	public ManagerListHandler getManagerListHandler() {
		return managerListHandler;
	}

}
