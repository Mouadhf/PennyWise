package com.pennywise.pw.repository;

import com.pennywise.pw.model.Category;
import com.pennywise.pw.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
