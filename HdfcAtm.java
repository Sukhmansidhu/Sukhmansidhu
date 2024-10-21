import java.util.Scanner;

class ATM {
    private double balance;
    private int pin;

    public ATM(double initialBalance, int inititalPin) {
        this.balance = initialBalance;
        this.pin = inititalPin;
    }

    public void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
            checkBalance();
        } else {
            System.out.println("Invalid deposit amount. Please try again.");

        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully Withdraw $" + amount);
            checkBalance();

        } else if (amount > balance) {
            System.out.println("Insufficient balance. Transaction failed.");
        } else {
            System.out.println("Invalid withdrwal amount. Please try again.");
        }

    }

    public void runATM() {
        Scanner sc = new Scanner(System.in);
        int attempts = 0;
        while (attempts < 3) {
            System.out.println("Please enter your PIN: ");
            int enteredPin = sc.nextInt();
            if (enteredPin == pin) {
                System.out.println("Pin accepted. Access Granted.");
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Attempts left: " + (3 - attempts));
                if (attempts == 3) {
                    System.out.println("Too many incorrect attempts. Existing...");
                    return;
                }
            }
        }
        while (true) {
            System.out.println("\nWelcome to the Hdfc ATM ");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");

            System.out.println("Please choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;

                case 2:
                    System.out.println("Enter the amount to Deposit: ");
                    double depositAmount = sc.nextDouble();
                    deposit(depositAmount);
                    break;

                case 3:
                    System.out.println("Enter the amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("Thank you for using the Hdfc ATM. Goodbye! ");
                    return;

                default:
                    System.out.println("Invalid Option. Please try again...");
            }
        }
    }
}

public class HdfcAtm {

    public static void main(String[] args) {
        ATM myAtm = new ATM(500.0, 1234);
        myAtm.runATM();
    }
}