package main;

public class RandomDataProvider {
    private static final String[] CLIENTNAME = {
            "Alice", "Bob", "Charlie", "David", "Eve",
            "Frank", "Grace", "Hannah", "Isaac", "Jack",
            "Katie", "Liam", "Mia", "Noah", "Olivia",
            "Paul", "Quinn", "Ruby", "Sophia", "Tom",
            "Uma", "Victor", "Wendy", "Xander", "Yara",
            "Zoe", "Adam", "Bella", "Carter", "Diana",
            "Ethan", "Fiona", "George", "Hailey", "Ian",
            "Julia", "Kyle", "Laura", "Mason", "Nina",
            "Oscar", "Piper", "Quincy", "Riley", "Sam",
            "Tina", "Uriel", "Vivian", "Will", "Zara"
        };
    private static final String[] MANAGER_NAMES = {
       "Amelia", "Benjamin", "Clara", "Daniel", "Ella",
            "Freddie", "Gabriel", "Hazel", "Ivy", "Jason"
    };

    private static final int[] CREDIT_VALUES = {1000, 5000, 10000, 20000};
    private static final int[] MOTHLY_PAYMENT = {1000, 5000, 10000, 70000};
    private static final int[] BUSINESS_REVENUE = {100,12300,23988, 50000, 10000000, 2000000};
    private static final int[] YEARS_FOR_MORTGAGE = {5, 10, 15, 20, 30};
    private static final int[] MORTGAGE_AMOUNTS = {50000, 100000, 500000, 1000000, 1500000, 2000000, 2500000, 3000000};
    private static final int[] DEPOSIT_AMOUNTS = {500, 1000, 1500, 2000, 3000, 4000, 5000, 7500, 10000};

    public static String getRandomClientName() {
        return CLIENTNAME[(int) (Math.random() * CLIENTNAME.length)];
    }

    public static String getRandomManagerName() {
        return MANAGER_NAMES[(int) (Math.random() * MANAGER_NAMES.length)];
    }

    public static int getRandomCredit() {
        return CREDIT_VALUES[(int) (Math.random() * CREDIT_VALUES.length)];
    }
    public static int getRandomMonthlyPayMent() {
        return MOTHLY_PAYMENT[(int) (Math.random() * MOTHLY_PAYMENT.length)];
    }
    public static int getRandomBusinessRevenue() {
        return BUSINESS_REVENUE[(int) (Math.random() * BUSINESS_REVENUE.length)];
    }

    public static int getRandomYearsForMortgage() {
        return YEARS_FOR_MORTGAGE[(int) (Math.random() * YEARS_FOR_MORTGAGE.length)];
    }

    public static int getRandomMortgageAmount() {
        return MORTGAGE_AMOUNTS[(int) (Math.random() * MORTGAGE_AMOUNTS.length)];
    }

    public static int getRandomDepositAmount() {
        return DEPOSIT_AMOUNTS[(int) (Math.random() * DEPOSIT_AMOUNTS.length)];
    }

    public static int getRandomRank() {
        return 1 + (int) (Math.random() * 10); // Random rank between 1 and 10
    }


}
