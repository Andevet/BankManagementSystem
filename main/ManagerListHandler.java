package main;

import java.util.*;

import static main.UserInput.s;

public class ManagerListHandler {
    private static AccountManager accountManager;

    public ManagerListHandler(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void managerWithLambdaExpression() {
		Set<String> treeSet = new TreeSet<>((n1, n2)->{
			int x = n1.length() -n2.length();
			return x == 0 ?  String.CASE_INSENSITIVE_ORDER.compare(n1, n2) : x;
		});

        for (Account acc : accountManager.getAccounts()) {
			if (acc != null){
				String lowerCaseName = acc.getManagerName().toLowerCase();
				treeSet.add(lowerCaseName);
			}
        }
		Iterator<String> it = treeSet.iterator();
		while (it.hasNext()){
			System.out.println(it.next().toUpperCase());
		}

	}

	public  void managerToArrayList(Bank bank) {
		Set<String> uniqueNames = new LinkedHashSet<>();
        for (Account acc : accountManager.getAccounts()) {
			if (acc != null){
				String lowerCaseName = acc.getManagerName().toLowerCase();
				uniqueNames.add(lowerCaseName);
			}

        }
		Set<String> set = uniqueNames;
		if (set != null){
			List<String> arrayList =  new ArrayList<>();
			 //  יצירת ריק ArrayList
			ListIterator<String> li = arrayList.listIterator();
			// והוספת כל מחרוזת פעמיים

            for (String name : set) {
				li.add(name);
				li.add(name);
			}
			// שימוש ב-ListIterator להדפסה מהסוף להתחלה
			while (li.hasPrevious()) {
				System.out.println(li.previous());
			}
		}
		runCustomIterator(bank);
	}


	private void runCustomIterator(Bank bank) {
		String name;
		while(true) {
            System.out.println("Do you want to see the output of my self-implemented iterators (Y/y or any other key \n" +
                    "to skip):");
            name  = s.nextLine();
			if (name.equalsIgnoreCase("y")){
				List<String> managerNamesList = AccountsFactory.createManagerNamesList(bank);
				IterationManager.printArrayWithMyIterator(bank,managerNamesList);
                IterationManager.printArrayWithMyListIterator(bank,managerNamesList);
			}
            break;
        }
	}

}
