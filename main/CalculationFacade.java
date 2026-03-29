package main;

public class CalculationFacade {
    private final Bank bank;

    public CalculationFacade() {
        this.bank = Bank.getInstance();
    }

    public void calculateAnnualProfit() {
        bank.getCalculationManager().annualProfit(bank);
    }

    public void allAnnualAccountProfit() {
        System.out.println("The profit from all accounts in the system:\n" + bank.getCalculationManager().calculateAllAccountsProfit());
    }

    public void influanceAccount() {
        System.out.println("The most Checking account profitable is - * \n" + bank.getCalculationManager().mostProfit());
    }

    public void checkProfitVIP() {
        bank.getCalculationManager().checkProfitVIP();
    }

    public void managerToArrayListReports() {
        bank.getCalculationManager().getManagerListHandler().managerToArrayList(bank);
    }
    public void managerWithLambdaExpressionReports() {
        bank.getCalculationManager().getManagerListHandler().managerWithLambdaExpression();
    }
}
