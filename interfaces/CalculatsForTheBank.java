package interfaces;

import bankEnum.AccountType;
import bankException.GeneralException;
import main.CheckingAccount;

public interface CalculatsForTheBank {
    float calculateProfit(int originalMortgageAmount, int years) throws GeneralException;
    float calculateAccountProfit(CheckingAccount account, AccountType type);
}
