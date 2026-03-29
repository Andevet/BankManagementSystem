package main;

import bankException.AmountExcption;
import bankException.NameException;
import bankException.RankException;

import java.util.Random;

import static main.Bank.checkString;
import static main.Bank.getInstance;
import static main.UserInput.s;

public class ClientHelper {

    private static final int MIN_CLIENTS = 2;
    private static final int MAX_CLIENTS = 4;

    public static boolean addNewClient() {
		int number;
		int findAccountIndex;
		System.out.println("you choose to add new client to the account\nPlease enter the account number");
		while(true) {
			try {
				number = Integer.parseInt(s.nextLine());
				findAccountIndex = getInstance().getAccountManager().findAccountIndex(number);
				if(findAccountIndex ==  -1) {
					throw new AmountExcption("");
				}
				break;
			}catch(NumberFormatException e) {
					System.out.println("wrong input try again");
			}
			catch(AmountExcption e) {
				System.out.println("The account number you entered  does not exist in the system");
				return false;
			}
		}
		String clientName;
		int clientRank;
		System.out.println("-----Enter the new client data-----");
		while(true) {
			try {
				System.out.println("Enter the client name");
				clientName = s.nextLine();
				boolean checkName = getInstance().getAccountManager().checkClientName(findAccountIndex,clientName);
				checkString(clientName);
				if (checkName){
					System.out.println("the name is already in the accounts ");
					return false;
				}
				break;
			}catch(NameException e) {
				System.out.println("you enter name with wrong characters, try again");
			}
        }
		while(true) {
			try {
				System.out.println("Enter the rank, the rank must be between (1 - 10):");
				clientRank = Integer.parseInt(s.nextLine());
				if (clientRank < 1 || clientRank > 10) {
					throw new RankException();
				}
				break;
			}catch(NumberFormatException | RankException e) {
				System.out.println("you must enter integer number between 1 - 10");
			}
		}
		while(true) {
			try {
					getInstance().getAccountManager().addClientData(clientName, clientRank, findAccountIndex);
					break;
				}catch(NameException e) {
					System.out.println(clientName + " is already in the system ");
				}
        }
		return true;
	}

    // Adding clients to account
    public static void addClientsToAccount(Bank bank, int accountNumber) {
        while (true) {
            try {
                clientAddSuccessfully(UserInput.clientData(bank, accountNumber),accountNumber);
                System.out.println("Would you like to add another client? (yes/no): ");
                String choice = s.nextLine().trim().toLowerCase();
                if (!choice.equals("yes")) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error adding client: " + e.getMessage());
            }
        }
    }

	public static void clientAddSuccessfully(boolean isSuccessful,int accountNumber){
        if (isSuccessful){
               System.out.println("Client added successfully to account number " + accountNumber);
        }
        else
               System.out.println("Client not added  to account number " + accountNumber);
    }

     // הוספת לקוחות רנדומליים לחשבון
    public static void addRandomClientsToAccount(Bank bank, int indexAccount) throws NameException {
        int numberOfClients = MIN_CLIENTS + new Random().nextInt(MAX_CLIENTS); // מספר רנדומלי של לקוחות בין 2 ל-5
        for (int i = 0; i < numberOfClients; i++) {
            String clientName = RandomDataProvider.getRandomClientName();
            int clientRank = RandomDataProvider.getRandomRank();
            try {
                if (!isClientNameExists(bank,clientName,indexAccount)){
                    bank.getAccountManager().addClientData(clientName, clientRank, indexAccount);
                    System.out.println("Client " + clientName + " added successfully to account " + indexAccount);
                }
                else {
                    System.out.println("Client " + clientName + " was not added to account " + indexAccount + " because the name already exists.");
                }

            } catch (NameException e) {
                System.out.println("Failed to add client " + clientName + " to account " + indexAccount + ": " + e.getMessage());
            }
        }
    }

    // פונקציה לבדיקה אם שם לקוח כבר קיים
    public static boolean isClientNameExists(Bank bank, String clientName, int indexAccount) {
        return bank.getAccountManager().checkClientName(indexAccount, clientName);
    }
}
