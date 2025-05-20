package org.example;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Income\n2. Add Expense\n3. Monthly Summary\n4. Load from File\n5. Save to File\n6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                case 2:
                    String type = (choice == 1) ? "income" : "expense";
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    Transaction t = new Transaction(type, category, amount, LocalDate.now());
                    tracker.addTransaction(t);
                    break;
                case 3:
                    System.out.print("Enter year and month (yyyy-MM): ");
                    YearMonth ym = YearMonth.parse(scanner.nextLine());
                    tracker.printMonthlySummary(ym);
                    break;
                case 4:
                    System.out.print("Enter file path: ");
                    String loadFile = scanner.nextLine();
                    tracker.loadFromFile(loadFile);
                    break;
                case 5:
                    System.out.print("Enter file path to save: ");
                    String saveFile = scanner.nextLine();
                    tracker.saveToFile(saveFile);
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }
}