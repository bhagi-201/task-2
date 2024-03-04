import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String userID;
    private String userPIN;
    private double accountBalance;

    public User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double newBalance) {
        accountBalance = newBalance;
    }
}

class ATM {
    private Map<String, User> userDatabase;
    private User currentUser;

    public ATM() {
        userDatabase = new HashMap<>();
        // Add sample user for testing
        userDatabase.put("veritech", new User("9876453210", "85461", 100.0));
    }

    public void start() {
        System.out.println("Welcome to the ATM");
        authenticateUser();
        displayMenu();
    }

    private void authenticateUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        User user = userDatabase.get(userID);

        if (user != null && user.getUserPIN().equals(pin)) {
            currentUser = user;
            System.out.println("Authentication successful. Welcome, " + userID + "!");
        } else {
            System.out.println("Invalid credentials. Exiting...");
            System.exit(0);
        }
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Your balance is: ₹" + currentUser.getAccountBalance());
    }

    private void withdrawMoney() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter withdrawal amount: ₹ ");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= currentUser.getAccountBalance()) {
            double newBalance = currentUser.getAccountBalance() - amount;
            currentUser.setAccountBalance(newBalance);
            System.out.println("Withdrawal successful. New balance: ₹ " + newBalance);
        } else {
            System.out.println("Invalid amount or insufficient funds. Please try again.");
        }
    }

    private void depositMoney() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter deposit amount: ₹");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            double newBalance = currentUser.getAccountBalance() + amount;
            currentUser.setAccountBalance(newBalance);
            System.out.println("Deposit successful. New balance: ₹" + newBalance);
        } else {
            System.out.println("Invalid amount. Please try again.");
        }
    }
}

public class ATMINTERFACE {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
