package main;

import bankEnum.AccountType;
import bankException.AmountExcption;
import bankException.GeneralException;
import bankException.MounthlyPaymentException;
import bankException.YearException;
import interfaces.ManagementFees;
import interfaces.Profitable;

public class MortgageAccount extends Account implements Profitable , ManagementFees {
	private int originalMortgageAmount;
	private int years;
	private float monthlyPayment;
	private final float PERCENTFROMTHEAMOUNT = 0.8F;
	private final float RateDifference = 0.1F;


	@Override
    public AccountType getAccountType() {
        return AccountType.MORTGAGEACCOUNT;
    }
	
 	public MortgageAccount(String managerName,int numberAccount,boolean auto,int originalMortgageAmount,int years,float monthlyPayment)throws GeneralException {
		super(managerName,numberAccount,auto);
		setOriginalMortgageAmount(originalMortgageAmount);
		setYears(years);
		setMonthlyPayment(monthlyPayment);
	}
	public void setMonthlyPayment(float monthlyPayment) throws MounthlyPaymentException {
		if(monthlyPayment <= 0.0f) {
			throw new MounthlyPaymentException();
		}
		else {
			this.monthlyPayment = monthlyPayment;
		}
		
	}
	public void setYears(int years) throws YearException{
		if(years <= 0 ) {
			throw new YearException();
		}
		else {
			this.years = years; 
		}
		
	}
	public void setOriginalMortgageAmount(int originalMortgageAmount)throws AmountExcption {
		if(originalMortgageAmount <= 0) {
			throw new AmountExcption("wrong input try again");
		}
		else {
			this.originalMortgageAmount = originalMortgageAmount;
		}
		
	}
	public int getOriginalMortgageAmount() {
		return originalMortgageAmount;
	}
	public int getYears() {
		return years;
	}
	public float getMonthlyPayment() {
		return monthlyPayment;
	}
	@Override
	public int profitCalculator() {
		return  (int) (((originalMortgageAmount * PERCENTFROMTHEAMOUNT )/ years) * RateDifference);
	}
	@Override
	public double fees() {
		return 0.1 * originalMortgageAmount;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName() +" \n------------\n" + super.toString()).append("The Mortgage detail on this accounts:\n_____________________________\n");
		sb.append(" -> The original mortgage Amount is - ").append(getOriginalMortgageAmount()).append(" NIS.\n");
		sb.append(" -> The mortgage period is - ").append(getYears()).append(".\n").append("The monthly payment is - ").append(getMonthlyPayment()).append(".\n");
		sb.append("\n----------------------------------------------\n");
		return sb.toString();
	}
}
