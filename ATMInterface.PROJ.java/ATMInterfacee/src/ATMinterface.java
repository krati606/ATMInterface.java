import java.util.Scanner;
class BankAccount {
    private String name;
    private String username;
    private String password; // Changed to lowercase for convention
    private String accountNo;
    private float balance = 0f;
    private int transactionCount = 0; // More descriptive variable name
    private StringBuilder transactionHistory = new StringBuilder(); // Using StringBuilder for efficiency

    private static Scanner sc = new Scanner(System.in); // Single Scanner instance for efficiency

    public void register() {
        System.out.print("Enter your Name: ");
        this.name = sc.nextLine();
        System.out.print("Enter your Username: ");
        this.username = sc.nextLine();
        System.out.print("Enter your Password: ");
        this.password = sc.nextLine();
        System.out.print("Enter your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("Registration Successful. Please log in to your Bank Account.");
    }

    public boolean login() {
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.print("Enter your Username: ");
            String inputUsername = sc.nextLine();
            if (inputUsername.equals(username)) {
                while (!isLoggedIn) {
                    System.out.print("Enter your Password: ");
                    String inputPassword = sc.nextLine();
                    if (inputPassword.equals(password)) {
                        System.out.println("Login Successful");
                        isLoggedIn = true;
                    } else {
                        System.out.println("Incorrect Password. Try again.");
                    }
                }
            } else {
                System.out.println("Username not found. Try again.");
            }
        }
        return isLoggedIn;
    }

    public void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Clear the buffer

        if (balance >= amount) {
            transactionCount++;
            balance -= amount;
            System.out.println("Withdrawal Successful");
            transactionHistory.append(amount).append(" Rs Withdrawn\n");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit() {
        System.out.print("Enter amount to deposit (Max 10,000 Rs): ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Clear the buffer

        if (amount <= 10000f) {
            transactionCount++;
            balance += amount;
            System.out.println("Deposit Successful");
            transactionHistory.append(amount).append(" Rs Deposited\n");
        } else {
            System.out.println("Sorry, the limit is 10,000 Rs.");
        }
    }

    public void transfer() {
        System.out.print("Enter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.print("Enter amount to transfer (Max 50,000 Rs): ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Clear the buffer

        if (balance >= amount) {
            if (amount <= 50000f) {
                transactionCount++;
                balance -= amount;
                System.out.println("Successfully Transferred to " + recipient);
                transactionHistory.append(amount).append(" Rs transferred to ").append(recipient).append("\n");
            } else {
                System.out.println("Sorry, the limit is 50,000 Rs.");
            }
        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + balance + " Rs");
    }

    public void viewTransactionHistory() {
        if (transactionCount == 0) {
            System.out.println("No transactions have occurred.");
        } else {
            System.out.println("Transaction History:\n" + transactionHistory.toString());
        }
    }
}

public class ATMinterface {
    private static int takeIntegerInput(int limit) {
        Scanner sc = new Scanner(System.in);
        int input = 0;

        while (true) {
            try {
                input = sc.nextInt();
                sc.nextLine(); // Clear the buffer
                if (input < 1 || input > limit) {
                    System.out.println("Choose a number between 1 and " + limit);
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Enter only integer value.");
                sc.next(); // Clear invalid input
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n*****************WELCOME TO ATM******************");
        System.out.println("1. Register \n2. Exit");
        System.out.print("Choose an option: ");
        int choice = takeIntegerInput(2);

        if (choice == 1) {
            BankAccount account = new BankAccount();
            account.register();

            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.print("Enter your choice: ");
                int ch = takeIntegerInput(2);
                if (ch == 1) {
                    if (account.login()) {
                        System.out.println("\n**********************WELCOME  TO  YOUR  BANK  ACCOUNT*********************");
                        boolean isFinished = false;

                        while (!isFinished) {
                            System.out.println("\n1. WITHDRAW \n2. DEPOSIT \n3. TRANSFER \n4. CHECK BALANCE \n5. TRANSACTION HISTORY \n6. EXIT");
                            System.out.print("Enter your choice: ");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1:
                                    account.withdraw();
                                    break;
                                case 2:
                                    account.deposit();
                                    break;
                                case 3:
                                    account.transfer();
                                    break;
                                case 4:
                                    account.checkBalance();
                                    break;
                                case 5:
                                    account.viewTransactionHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    System.out.println("Exiting...");
                                    break;
                            }
                        }
                    }
                } else {
                    System.out.println("Exiting...");
                    break;
                }
            }
        }
    }
}
