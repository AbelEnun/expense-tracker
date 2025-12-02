// src/main/java/com/example/expensetracker/controller/ExpenseController.java
package com.abel.expensetracker.controller;

import com.abel.expensetracker.dto.ExpenseDto;
import com.abel.expensetracker.dto.UpdateExpenseDto;
import com.abel.expensetracker.model.Expense;
import com.abel.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> list(@RequestParam String email) {
        return ResponseEntity.ok(expenseService.getUserExpenses(email));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ExpenseDto dto) {
        Expense saved = expenseService.addExpense(dto.getEmail(), dto.getCategory(), dto.getDescription(), dto.getAmount());
        if (saved == null) return ResponseEntity.badRequest().body("User not found");
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UpdateExpenseDto dto) {
        Expense updated = expenseService.updateExpense(dto.getEmail(), id, dto.getCategory(), dto.getDescription(), dto.getAmount());
        if (updated == null) return ResponseEntity.badRequest().body("Expense not found or not owned by user");
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id, @RequestParam String email) {
        boolean ok = expenseService.deleteExpense(email, id);
        if (!ok) return ResponseEntity.badRequest().body("Expense not found or not owned by user");
        return ResponseEntity.noContent().build();
    }
}
