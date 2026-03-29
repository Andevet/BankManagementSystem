//IM TH ONE
//Idan Brains, id: 207023623
//Andevet Mola, id: 327220711
package main;

import java.util.Scanner;

import bankException.NameException;

import static main.AccountsFactory.checkLetter;


public class Main {
	private static final Scanner s = new Scanner(System.in);
	private static final AccountFacade accountFacade = new AccountFacade();
    private static final ReportFacade reportFacade = new ReportFacade();
    private static final CalculationFacade calculationFacade = new CalculationFacade();
	public static void main(String[] args) {
		System.out.println("Welcome to the bank system\n======Menu======");
		initializeBankFacade();  // קריאה לפונקציה שתגדיר את הבנק וה-Observers
		String input = "";
		int choice;
        do {
			printMenu();
			while(true) {
				try {
					input = s.nextLine();
					choice = Integer.parseInt(input);
					break;
				}catch(NumberFormatException e) {
					if(!checkLetter(input)) {
						choice = 0;
						break;
					}
					else {
						System.out.println(e.getMessage());
					}
				}
			}
            try {
                switchCase(choice);
            } catch (NumberFormatException | NameException e) {
                 System.out.println(e.getMessage());
            }
        }while( choice != 0);
		s.close();
	}

	private static void initializeBankFacade() {
		accountFacade.initializeBank();
    }
	private static void switchCase(int choice) throws NumberFormatException, NameException {
		switch(choice) {
		case 0 -> exitProgram();
		case 1 ->   accountFacade.uploadAutoData();
		case 2 ->   accountFacade.createAccount();
		case 3 ->   accountFacade.addClientToAccount();
		case 4 ->   reportFacade.printAllAccounts();
		case 5 ->   reportFacade.printProfitAccounts();
		case 6 ->   reportFacade.showSpecificDataKind();
		case 7 ->   calculationFacade.calculateAnnualProfit();
		case 8 ->   calculationFacade.allAnnualAccountProfit();
		case 9 ->   calculationFacade.influanceAccount();
		case 10 ->  calculationFacade.checkProfitVIP();
		case 11 ->  reportFacade.printAllManagementFees();
		case 99 ->  reportFacade.printAllManagers();
		case 100 -> reportFacade.printUniqueManagers();
		case 101 -> reportFacade.countManagerOccurrences();
		case 102 -> calculationFacade.managerToArrayListReports();//השארתי את זה בבנק כרגע
		case 103 -> calculationFacade.managerWithLambdaExpressionReports();
		default -> System.out.println("wrong input");
		}
	}
	private static void exitProgram() {
		System.out.println("Exit the program");
	}
	private static void printMenu() {
        System.out.println("1. To uplaod auto data for the bank account, press 1");
        System.out.println("2. To add a new bank account press 2");
        System.out.println("3. To add a new client into existed bank account press 3");
        System.out.println("4. For account's data press 4");
        System.out.println("5. For profit account press 5");
        System.out.println("6. To show kind account press 6");
        System.out.println("7. for the bank annual profit from choosen account  7");
        System.out.println("8. for the bank annual profit from all the accounts press 8");
        System.out.println("9. for the most signficant  annual prfit bank  משפיע account press 9");
        System.out.println("10. checkProfitVIPמימוש תזית של המתודה - 9");
        System.out.println("11. printManagmentFees הפעלת מתודה זו");
        System.out.println("12. To exit press e");
        System.out.println("99. To print all manager names of the accounts: ");
        System.out.println("100. To print all manager names of the accounts Without Duplication: ");
        System.out.println("101. To print number of time manager name appears in the original array account : ");
        System.out.println("102. To print  duplicate manager array list in reverse: ");
        System.out.println("103. To print manager array list in name length order: ");
        System.out.print("Enter your choice --> ");
	}

}




