package main;

import bankEnum.AccountType;
import bankException.GeneralException;
import interfaces.Profitable;

public class RegularCheckingAccount extends CheckingAccount implements Profitable {
	public RegularCheckingAccount(String managerName, int inputAccountNumber, boolean autoId, int credit) throws GeneralException
		{
			super(managerName,inputAccountNumber,autoId,credit);
	   }

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(":\n______\n").append(getClass().getSimpleName() +" " + super.toString());
		sb.append("\n____________________________\n");
		return sb.toString();
		}
		@Override
	public int profitCalculator() {
		return (int) (getCredit() * getRateDifference());
	}


	@Override
    public AccountType getAccountType() {
        return AccountType.REGULARCHECKINGACCOUNT;
    }

}
