package main;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AccountListCreator {
    public static List<String> createManagerNamesList(Bank bank) {
        Set<String> uniqueNames = new LinkedHashSet<>();
        Account[] accounts = bank.getAccountManager().getAccounts();

        // Collect unique manager names
        for (Account acc : accounts) {
            if (acc != null) {
                String lowerCaseName = acc.getManagerName().toLowerCase();
                uniqueNames.add(lowerCaseName);
            }
        }

        // Create a list with each name appearing twice
        List<String> managerNamesList = new ArrayList<>();
        for (String name : uniqueNames) {
            managerNamesList.add(name);
            managerNamesList.add(name);
        }

        return managerNamesList;
    }
}
