package com.pennywise.pw.client;

import com.pennywise.pw.client.model.Category;
import com.pennywise.pw.client.model.Expense;

public class ExpenseClientDemo {
    public static void main(String[] args) {
        ExpenseClient client = new ExpenseClient();

        // Example: Add a new expense
        try {
            String result = client.addExpense(
                "Groceries",
                50.00,
                "Weekly shopping",
                100.00,
                Category.FOOD
            );
            System.out.println("Add Expense Result: " + result);
        } catch (Exception e) {
            System.err.println("Error adding expense: " + e.getMessage());
        }

        // Example: Get an expense
        try {
            Expense expense = client.getExpense(1);
            System.out.println("Retrieved Expense: " + expense);
        } catch (Exception e) {
            System.err.println("Error getting expense: " + e.getMessage());
        }

        // Example: Update an expense
        try {
            String result = client.updateExpense(
                1,
                "Restaurant",
                75.00,
                "Dinner with friends",
                100.00,
                Category.ENTERTAINMENT
            );
            System.out.println("Update Expense Result: " + result);
        } catch (Exception e) {
            System.err.println("Error updating expense: " + e.getMessage());
        }

        // Example: Delete an expense
        try {
            String result = client.deleteExpense(1);
            System.out.println("Delete Expense Result: " + result);
        } catch (Exception e) {
            System.err.println("Error deleting expense: " + e.getMessage());
        }
    }
} 