// src/main/java/com/example/expensetracker/model/Expense.java
package com.abel.expensetracker.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a constructor with no arguments
@AllArgsConstructor // Generates a constructor with all arguments
@Document(collection = "expenses")
public class Expense {
    
    @Id
    private String id;
    private String userId;
    private String category;
    private String description;
    private BigDecimal amount;
    private Instant createdAt;
    
    
}