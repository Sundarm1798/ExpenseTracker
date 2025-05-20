package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class ExpenseTracker {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            // Expected format: type,category,amount,date
            String[] parts = line.split(",");
            Transaction t = new Transaction(parts[0], parts[1], Double.parseDouble(parts[2]), LocalDate.parse(parts[3]));
            transactions.add(t);
        }
        reader.close();
    }

    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Transaction t : transactions) {
            writer.write(t.getType() + "," + t.getCategory() + "," + t.getAmount() + "," + t.getDate() + "\n");
        }
        writer.close();
    }

    public void printMonthlySummary(YearMonth month) {
        double totalIncome = 0;
        double totalExpense = 0;
        for (Transaction t : transactions) {
            if (YearMonth.from(t.getDate()).equals(month)) {
                if (t.getType().equalsIgnoreCase("income")) totalIncome += t.getAmount();
                else totalExpense += t.getAmount();
            }
        }
        System.out.println("Summary for " + month + ":");
        System.out.println("Income: " + totalIncome);
        System.out.println("Expense: " + totalExpense);
        System.out.println("Net Savings: " + (totalIncome - totalExpense));
    }
}

