// src/main/java/com/example/expensetracker/repository/OtpRepository.java
package com.abel.expensetracker.repository;

import com.abel.expensetracker.model.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpRepository extends MongoRepository<Otp, String> {
    Optional<Otp> findTopByUserIdOrderByExpiresAtDesc(String userId);
}
