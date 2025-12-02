// src/main/java/com/example/expensetracker/service/EmailService.java
package com.abel.expensetracker.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Expense Tracker OTP");
        message.setText("Your OTP is: " + otp + " (valid for 10 minutes)");
        mailSender.send(message);
    }
}
