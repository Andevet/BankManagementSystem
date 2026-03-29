package main;

import java.util.List;

public class  IterationManager {
    public static void printArrayWithMyIterator(Bank bank, List<String> managerNamesList){
		bank.getAccountManager().setItems(managerNamesList);
		Bank.CustomIterator iterator = new Bank.CustomIterator(managerNamesList,bank);

		System.out.println("Iterating forward with Iterator: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
		bank.getBankObserver().notify("Iteration with iterator ended!");



	}

    public static void printArrayWithMyListIterator(Bank bank, List<String> managerNamesList) {
		Bank.CustomListIterator listIterator = new Bank.CustomListIterator(managerNamesList,bank);
        System.out.println("------------------------ ");
        System.out.println("Iterating forward with List Iterator: ");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
		bank.getBankObserver().notify("Iteration with ListIterator ended!");

        System.out.println("\nIterating backward with List Iterator:");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
		bank.getBankObserver().notify("Iteration with ListIterator ended!");
	}

}
