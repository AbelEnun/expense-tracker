// src/main/java/com/example/expensetracker/repository/UserRepository.java
package com.abel.expensetracker.repository;

import com.abel.expensetracker.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
