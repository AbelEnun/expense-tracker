package com.abel.expensetracker.controller;

import com.abel.expensetracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // Request an OTP for a given email
    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@RequestParam String email) {
        userService.requestOtp(email);
        return ResponseEntity.ok("OTP sent to " + email);
    }

    // Verify the OTP for a given email
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean ok = userService.verifyOtp(email, otp);
        if (!ok) {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }
        return ResponseEntity.ok("Login successful for " + email);
    }
}
