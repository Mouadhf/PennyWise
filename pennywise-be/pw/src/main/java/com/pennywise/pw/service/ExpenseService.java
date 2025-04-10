package com.pennywise.pw.service;

import com.pennywise.pw.model.Expense;
import com.pennywise.pw.model.Category;
import com.pennywise.pw.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public boolean addExpense(String title, double amount, String description, double limit, Category category) {
        try {
            Expense expense = new Expense();
            expense.setTitle(title);
            expense.setAmount(amount);
            expense.setDescription(description);
            expense.setExpenseLimit(limit);
            expense.setCategory(category);
            expenseRepository.save(expense);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Optional<Expense> getExpense(int id) {
        return expenseRepository.findById(id);
    }
    public boolean updateExpense(int id, String title, double amount, String description, double limit, Category category) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();
            expense.setTitle(title);
            expense.setAmount(amount);
            expense.setDescription(description);
            expense.setExpenseLimit(limit);
            expense.setCategory(category);
            expenseRepository.save(expense);
            return true;
        }
        return false;
    }
    public boolean deleteExpense(int id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
