// src/main/java/com/example/expensetracker/service/UserService.java
package com.abel.expensetracker.service;

import com.abel.expensetracker.model.User;
import com.abel.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final OtpService otpService;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, OtpService otpService, EmailService emailService) {
        this.userRepository = userRepository;
        this.otpService = otpService;
        this.emailService = emailService;
    }

    public User registerOrGetUser(String email) {
        return userRepository.findByEmail(email)
            .orElseGet(() -> {
                User user = new User();
                user.setEmail(email);
                user.setVerified(false);
                return userRepository.save(user);
            });
    }

    public void requestOtp(String email) {
        User user = registerOrGetUser(email);
        String otp = otpService.generateOTP();
        otpService.saveOTP(user.getId(), otp);
        emailService.sendOtp(email, otp);
    }

    public boolean verifyOtp(String email, String otpCode) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return false;
        boolean ok = otpService.verifyOTP(user.getId(), otpCode);
        if (ok) {
            user.setVerified(true);
            userRepository.save(user);
        }
        return ok;
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
