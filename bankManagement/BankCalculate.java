package bankManagement;
import bankException.AmountExcption;
import bankException.GeneralException;
import bankException.VIPException;
import bankException.YearException;
import interfaces.CalculatsForTheBank;
import main.BusinessCheckingAccount;
import main.CheckingAccount;
import bankEnum.AccountType;

public class BankCalculate implements CalculatsForTheBank {
	
	private static final int COMMISSION = 3000;
	private static final float RATEDIFFERENCE = 0.1f;


	public float calculateProfit(int originalMortgageAmount, int years) throws GeneralException{
    	if(years <= 0) {
    		throw new YearException();
    	}
    	if(originalMortgageAmount <= 0) {
    		throw new AmountExcption("wronge Mortagage Ampunt inputs");
    	}
    	return approximateCalculation(originalMortgageAmount, years);
    }

    public float calculateAccountProfit(CheckingAccount account, AccountType type)  {
        if (type == AccountType.REGULARCHECKINGACCOUNT) {
            return RATEDIFFERENCE * account.getCredit();
        }
        if (type == AccountType.BUSINESSCHECKINGACCOUNT) {
            if (isVIPAccount(account)) {
                return 1.0f;
            }
            return RATEDIFFERENCE * account.getCredit() + COMMISSION;
        }
      return 0;  
    }
 	private boolean isVIPAccount(CheckingAccount account) {
		if (account instanceof BusinessCheckingAccount){
			if(((BusinessCheckingAccount) account).getBusinessRevenue()>= 10000000 && cheackRank(account)) {
 			return true;
		   }
 		}
 		 return false;

	}

	private boolean cheackRank(CheckingAccount account) {
		int count = 0;
		for(int i = 0; i < account.getClients().length;i++) {
				if(account.getClients()[i].getClientRank() == 10) {
					count++;
				}
			}
			if(count == account.getClientCounter()) {
				return true;
			}
		return false;
	}

	public float approximateCalculation(int originalMortgageAmount, int years){
		float eitheenPresentFromMortgage = (float)(originalMortgageAmount * 0.8f);
		float mortgageDivYears = (float)(eitheenPresentFromMortgage / years);
		return mortgageDivYears * RATEDIFFERENCE;
	}

}
