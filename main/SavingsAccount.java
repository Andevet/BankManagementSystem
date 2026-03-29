package main;

import bankEnum.AccountType;
import bankException.AmountExcption;
import bankException.GeneralException;
import bankException.YearException;

public class SavingsAccount extends Account {
	private int depositAmount;
	private int years;
	public SavingsAccount(String managerName, int numberAccount, boolean auto, int year, int depositAmount )throws GeneralException {
		super(managerName,numberAccount,auto);
		setDepositAmount(depositAmount);
		setYears(year);
		
	}

	@Override
    public AccountType getAccountType() {
        return AccountType.SAVINGACCOUNT;
    }
	public int getYears() {
		return years;
	}
	public int getDepositAmount() {
		return depositAmount;
	}
	

	public void setDepositAmount(int depositAmount)throws AmountExcption{
		if(depositAmount <= 0) {
			throw new AmountExcption("you enter unlogic acccount he must be above 0");
		}
		this.depositAmount = depositAmount;
	}
	public void setYears(int years) throws YearException{
		if(years <= 0)
			throw new YearException();
		else
			this.years = years;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName() + ": \n"+ super.toString()).append("The details on the saving accounts:\n_____________________________\n");
		sb.append(" -> The saving period is - ").append(getYears()).append(".\n").append("The mounthly deposit is - ").append(getDepositAmount()).append(".\n");
		sb.append("\n----------------------------------------------\n");
		return sb.toString();
	}



}
