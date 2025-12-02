// src/main/java/com/example/expensetracker/repository/ExpenseRepository.java
package com.abel.expensetracker.repository;

import com.abel.expensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findByUserId(String userId);
}
