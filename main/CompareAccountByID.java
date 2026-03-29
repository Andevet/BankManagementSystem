package main;

import java.util.Comparator;

public class CompareAccountByID implements Comparator<Account>{
	public int compare(Account a1, Account a2) {
		if(a1.getAccountNumber() > a2.getAccountNumber()) {
			return 1;
		}
		else if (a1.getAccountNumber() < a2.getAccountNumber()) {
			return -1;
			
		}
		else
			return 0;
	}
	
	

}
