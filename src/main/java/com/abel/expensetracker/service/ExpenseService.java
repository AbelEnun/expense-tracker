// src/main/java/com/example/expensetracker/service/ExpenseService.java
package com.abel.expensetracker.service;

import com.abel.expensetracker.model.Expense;
import com.abel.expensetracker.model.User;
import com.abel.expensetracker.repository.ExpenseRepository;
import com.abel.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public List<Expense> getUserExpenses(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return List.of();
        return expenseRepository.findByUserId(userOpt.get().getId());
    }

    public Expense addExpense(String email, String category, String description, BigDecimal amount) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return null;

        Expense exp = new Expense();
        exp.setUserId(user.getId());
        exp.setCategory(category);
        exp.setDescription(description);
        exp.setAmount(amount);
        exp.setCreatedAt(Instant.now());
        return expenseRepository.save(exp);
    }

    public Expense updateExpense(String email, String expenseId, String category, String description, BigDecimal amount) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return null;

        Optional<Expense> found = expenseRepository.findById(expenseId);
        if (found.isEmpty()) return null;

        Expense exp = found.get();
        if (!exp.getUserId().equals(user.getId())) return null;

        if (category != null) exp.setCategory(category);
        if (description != null) exp.setDescription(description);
        if (amount != null) exp.setAmount(amount);
        return expenseRepository.save(exp);
    }

    public boolean deleteExpense(String email, String expenseId) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return false;

        Optional<Expense> found = expenseRepository.findById(expenseId);
        if (found.isEmpty()) return false;

        Expense exp = found.get();
        if (!exp.getUserId().equals(user.getId())) return false;

        expenseRepository.deleteById(expenseId);
        return true;
    }
}
