package main;

import bankEnum.AccountType;
import bankException.AmountExcption;
import bankException.NameException;
import bankException.RankException;

import java.util.Scanner;


public class UserInput {
    public static final Scanner s = new Scanner(System.in);

    public static AccountType getAccountTypeFromUser() {
        while (true) {
            try {
                System.out.println("Select account type:");
                System.out.println("1. Regular Checking Account");
                System.out.println("2. Business Checking Account");
                System.out.println("3. Mortgage Account");
                System.out.println("4. Savings Account");
                System.out.println("5. Return to Main Menu");

                int accountTypeChoice = Integer.parseInt(s.nextLine());

                switch (accountTypeChoice) {
                    case 1:
                        return AccountType.REGULARCHECKINGACCOUNT;
                    case 2:
                        return AccountType.BUSINESSCHECKINGACCOUNT;
                    case 3:
                        return AccountType.MORTGAGEACCOUNT;
                    case 4:
                        return AccountType.SAVINGACCOUNT;
                    case 5:
                        System.out.println("Returning to main menu...");
                        return null; // Indicate return to main menu
                    default:
                        System.out.println("Invalid account type selected. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
    }



    public static String getValidManagerName() throws NameException {
        while (true) {
            try {
                System.out.println("Enter the manager's name: ");
                String name = s.nextLine();
                Bank.checkString(name);
                return name;
            } catch (NameException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }
    }


    public static int getAccountNumber(boolean auto) {
        return AccountManager.addNumberAccount(auto);
    }

    public static int getCreditInput() {
        while (true) {
            try {
                System.out.println("Enter the credit for the account: ");
                return Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You must enter a valid integer. Try again.");
            }
        }
    }

    public static int getBusinessRevenue() {
        while (true) {
            try {
                System.out.println("Enter the business revenue: ");
                int revenue = Integer.parseInt(s.nextLine());
                if (revenue <= 0) {
                    throw new AmountExcption("You entered an invalid number.");
                }
                return revenue;
            } catch (NumberFormatException | AmountExcption e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }
    }

    public static int getMortgageDetails(String detailType) {
        while (true) {
            try {
                System.out.println("Enter the " + detailType + ": ");
                return Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You must enter a valid integer. Try again.");
            }
        }
    }
    public static int getMonthlyPayment(String detailType) {
        while (true) {
            try {
                System.out.println("Enter the " + detailType + ": ");
                return Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You must enter a valid integer. Try again.");
            }
        }
    }

    public static int getDepositAmount() {
        while (true) {
            try {
                System.out.println("Enter the deposit amount: ");
                return Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You must enter a valid integer. Try again.");
            }
        }
    }

    public static int getYears(String detailType) {
        while (true) {
            try {
                System.out.println("Enter the " + detailType + " (in years): ");
                return Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You must enter a valid integer. Try again.");
            }
        }
    }

    public static boolean clientData(Bank bank, int accountNumber) throws NameException {
        String nameOfTheClient;
        int clientRank;

        System.out.println("====Enter the client data====");
        while (true) {
            try {
                System.out.println("Enter the client name:");
                nameOfTheClient = s.nextLine();
                Bank.checkString(nameOfTheClient);
                break;
            } catch (NameException e) {
                System.out.println("You entered a name with invalid characters. Try again.");
            }
        }

        while (true) {
            try {
                System.out.println("Enter the rank, the rank must be between (1 - 10):");
                clientRank = Integer.parseInt(s.nextLine());
                if (clientRank < 1 || clientRank > 10) {
                    throw new RankException();
                }
                break;
            } catch (NumberFormatException | RankException e) {
                System.out.println("You must enter an integer between 1 and 10. Try again.");
            }
        }

        return bank.getAccountManager().addNewClientData(nameOfTheClient, clientRank, accountNumber);

    }
}


