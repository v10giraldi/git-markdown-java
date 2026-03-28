import java.util.Scanner;

public class AccountBank {
    private double balance;
    private double limitOverdraft;
    private double availableOverdraft;
    private double tax;
    private double initialLimit;

    public AccountBank(double initialDeposit) {
        this.balance = initialDeposit;
        if (initialDeposit <= 500) {
            this.limitOverdraft = 50;
        } else {
            this.limitOverdraft = initialDeposit * 0.5;
        }
        this.initialLimit = this.limitOverdraft;
        this.availableOverdraft = this.limitOverdraft;
        this.tax = 0;
    }

    public void checkBalance() {
        System.out.printf("Current Balance: R$ %.2f\n", balance);
    }

    public void checkLimitOverdraft() {
        System.out.printf("Overdraft limit: R$ %.2f | Available: R$ %.2f\n", limitOverdraft, availableOverdraft);
    }

    public void withdraw(double value) {
        if (value <= balance) {
            balance -= value;
        } else if (value <= balance + availableOverdraft) {
            double overdraftUsed = value - balance;
            balance = 0;
            availableOverdraft -= overdraftUsed;
            tax += overdraftUsed * 0.2; // 20%
            System.out.println("You used the overdraft. 20% tax will be charged when you deposit.");
        } else {
            System.out.println("Insufficient funds!");
            return;
        }
        System.out.printf("Withdraw of R$ %.2f completed.\n", value);
    }

    public void deposit(double value) {
        if (tax > 0) {
            if (value >= tax) {
                value -= tax;
                System.out.printf("Tax of R$ %.2f paid.\n", tax);
                tax = 0;
            } else {
                tax -= value;
                System.out.printf("Partial tax payment. Remaining: R$ %.2f\n", tax);
                return;
            }
        }
        // Refill overdraft if used
        double overdraftMissing = limitOverdraft - availableOverdraft;
        if (overdraftMissing > 0) {
            double toRefill = Math.min(overdraftMissing, value);
            availableOverdraft += toRefill;
            value -= toRefill;
        }
        balance += value;
        System.out.printf("Deposit of R$ %.2f completed.\n", value);
    }

    public void payBankSlip(double value) {
        System.out.println("Paying bank slip of R$ " + value + "...");
        withdraw(value);
    }

    public void verifyUsingOverdraft() {
        if (availableOverdraft < limitOverdraft) {
            System.out.println("You are using the overdraft.");
        } else {
            System.out.println("You are not using the overdraft.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the amount to open the account: R$ ");
        double initialDeposit = scanner.nextDouble();
        AccountBank account = new AccountBank(initialDeposit);

        int option;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Check balance");
            System.out.println("2. Check overdraft");
            System.out.println("3. Deposit money");
            System.out.println("4. Withdraw money");
            System.out.println("5. Pay bank slip");
            System.out.println("6. Verify overdraft usage");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    account.checkLimitOverdraft();
                    break;
                case 3:
                    System.out.print("Enter deposit amount: R$ ");
                    double depValue = scanner.nextDouble();
                    account.deposit(depValue);
                    break;
                case 4:
                    System.out.print("Enter withdraw amount: R$ ");
                    double withValue = scanner.nextDouble();
                    account.withdraw(withValue);
                    break;
                case 5:
                    System.out.print("Enter bank slip value: R$ ");
                    double slipValue = scanner.nextDouble();
                    account.payBankSlip(slipValue);
                    break;
                case 6:
                    account.verifyUsingOverdraft();
                    break;
                case 0:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (option != 0);

        scanner.close();
    }
}