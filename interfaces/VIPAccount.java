package interfaces;

import bankEnum.AccountType;
import main.CheckingAccount;

public interface VIPAccount {
    boolean isVIPAccount(CheckingAccount account, AccountType type);

}
