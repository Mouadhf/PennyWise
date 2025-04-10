package com.pennywise.pw.controller;

import com.pennywise.pw.model.Category;
import com.pennywise.pw.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(
            @RequestParam String title,
            @RequestParam double amount,
            @RequestParam String description,
            @RequestParam double limit,
            @RequestParam Category category) {
        if (expenseService.addExpense(title, amount, description, limit, category)) {
            return ResponseEntity.status(HttpStatus.OK).body("Expense added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add expense");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getExpense(@PathVariable int id) {
        return expenseService.getExpense(id)
                .<ResponseEntity<Object>>map(expense -> ResponseEntity.ok(expense))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<com.pennywise.pw.model.Expense>> getAllExpenses() {
        List<com.pennywise.pw.model.Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExpense(
            @PathVariable int id,
            @RequestParam String title,
            @RequestParam double amount,
            @RequestParam String description,
            @RequestParam double limit,
            @RequestParam Category category) {
        if (expenseService.updateExpense(id, title, amount, description, limit, category)) {
            return ResponseEntity.status(HttpStatus.OK).body("Expense updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id) {
        if (expenseService.deleteExpense(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Expense deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
        }
    }
}