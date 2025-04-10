package com.pennywise.pw.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String description;

    @Column(name = "expense_limit", nullable = false)
    private double expenseLimit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    public Expense(int id, String title, double amount, String description, double expenseLimit, Category category) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.description = description;
        this.expenseLimit = expenseLimit;
        this.category = category;
    }

    public Expense() {
    }
}
