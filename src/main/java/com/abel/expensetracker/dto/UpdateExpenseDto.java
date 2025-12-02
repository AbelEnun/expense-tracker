// src/main/java/com/example/expensetracker/dto/UpdateExpenseDto.java
package com.abel.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class UpdateExpenseDto {
    @NotBlank
    private String email;

    private String category;
    private String description;
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
