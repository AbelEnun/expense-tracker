// src/main/java/com/example/expensetracker/controller/AuthController.java
package com.abel.expensetracker.controller;

import com.abel.expensetracker.dto.RequestOtpDto;
import com.abel.expensetracker.dto.VerifyOtpDto;
import com.abel.expensetracker.model.User;
import com.abel.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@Valid @RequestBody RequestOtpDto dto) {
        userService.requestOtp(dto.getEmail());
        return ResponseEntity.ok().body("OTP sent to " + dto.getEmail());
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@Valid @RequestBody VerifyOtpDto dto) {
        boolean ok = userService.verifyOtp(dto.getEmail(), dto.getOtp());
        if (!ok) return ResponseEntity.badRequest().body("Invalid or expired OTP");
        User user = userService.getByEmail(dto.getEmail());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(@RequestParam String email) {
        User user = userService.getByEmail(email);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.isVerified() ? "verified" : "unverified");
    }
}
