package main;

import bankEnum.AccountType;
import bankEnum.CheckingVip;
import bankException.GeneralException;
import interfaces.ManagementFees;
import interfaces.Profitable;
import interfaces.Vipable;


public class BusinessCheckingAccount extends CheckingAccount implements ManagementFees , Profitable , Vipable {
	private CheckingVip checkingVip;
	private int  businessRevenue;
	private final int commission  = 3000;
    private final static int COUNTER = 1000000;


    @Override
    public AccountType getAccountType() {
        return AccountType.BUSINESSCHECKINGACCOUNT;
    }

	public BusinessCheckingAccount(String nameOfTheManager, int userInputForTheAccountNumber, boolean auto, int credit,
								   int businessRevenue)throws GeneralException {
		super(nameOfTheManager,userInputForTheAccountNumber,auto,credit);
		setBusinessRevenue(businessRevenue);
	}

	public int getBusinessRevenue() {
		return businessRevenue;
	}

	public void setBusinessRevenue(int businessRevenue) {
		this.businessRevenue = businessRevenue;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName()).append(":\n______\n").append(super.toString());
		sb.append(" \n->The business  revenue is - ").append(getBusinessRevenue());
		sb.append("\n____________________________\n");
		return sb.toString();
	}

	@Override
    public double fees() {
        return 1000;
    }

    public BusinessCheckingAccount(BusinessCheckingAccount original) throws GeneralException {
        super(original.getManagerName(),original.getAccountNumber(), true, original.getBusinessRevenue());



    // העתקת הלקוחות
    for (Client client : original.getClients()) {
        this.addClient(new Client(client.getName(), client.getClientRank()));
    }
}
	@Override
    public int calculateVipProfit() {
        if (!isVip()) {
            return 0; // אם לא VIP, אין רווח אבוד
        }
        // חישוב הרווח שהבנק מפסיד אם החשבון היה רגיל
        return (int) ((getCredit() * getRateDifference()) + commission); // כולל עמלה שנתית נוספת
    }

	 @Override
    public int profitCalculator() {
        if (isVip()) {
            return 0; // אין רווח מ-VIP
        }
        return (int) ((getCredit() * getRateDifference()) + commission);
    }


	@Override
    public boolean isVip() {
		checkingVip = CheckingVip.VIP;
        for (int i = 0; i < getClientCounter(); i++) {
            if (getClients()[i].getClientRank() != 10){
                checkingVip = CheckingVip.NOTVIP;
                return false;
                }
        }
        if (businessRevenue >= COUNTER && checkingVip == CheckingVip.VIP){
            return true;
        }
        return false;
    }

}
