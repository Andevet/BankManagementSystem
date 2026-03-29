package main;

import bankException.AmountExcption;
import bankException.GeneralException;

public abstract class CheckingAccount extends Account  {
	private int credit;
	private final float rateDifference = 0.1f;

	public CheckingAccount(String managerName, int numberAccount, boolean auto, int credit) throws GeneralException {
		super(managerName, numberAccount, auto);
		setCredit(credit);
	}

	public CheckingAccount(CheckingAccount other) throws GeneralException {
		super(other.getManagerName(), other.getAccountNumber(), false);
		setCredit(other.credit);
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) throws AmountExcption {
		if (credit <= 0) {
			throw new AmountExcption("credit number.Its must be above 0\n");
		} else {
			this.credit = credit;
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("\nThe credit on this account is: ").append(getCredit()+" ");
		return sb.toString();
	}


}



