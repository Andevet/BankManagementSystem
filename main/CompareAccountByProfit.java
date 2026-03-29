package main;

import java.util.Comparator;

import interfaces.Profitable;

public class CompareAccountByProfit implements Comparator<Account>{
	
	public int compare(Account account1,Account account2) {
		if (account1 instanceof Profitable && account2 instanceof Profitable){
			int x = ((Profitable) account1).profitCalculator() - ((Profitable) account2).profitCalculator();
			if(x < 0) return 1;
			if(x > 0) return -1;
		}
        return 0;
	}


}
