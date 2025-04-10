package com.pennywise.pw.client.model;

public class Expense {
    private int id;
    private String title;
    private double amount;
    private String description;
    private double expenseLimit;
    private Category category;

    public Expense() {
    }

    public Expense(int id, String title, double amount, String description, double expenseLimit, Category category) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.description = description;
        this.expenseLimit = expenseLimit;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getExpenseLimit() {
        return expenseLimit;
    }

    public void setExpenseLimit(double expenseLimit) {
        this.expenseLimit = expenseLimit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", expenseLimit=" + expenseLimit +
                ", category=" + category +
                '}';
    }
} 