// src/main/java/com/example/expensetracker/dto/ExpenseDto.java
package com.abel.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ExpenseDto {
    @NotBlank
    private String email;

    @NotBlank
    private String category;

    private String description;

    @NotNull
    private BigDecimal amount;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
