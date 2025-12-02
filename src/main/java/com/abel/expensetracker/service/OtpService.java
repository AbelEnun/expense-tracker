package com.abel.expensetracker.service;

import com.abel.expensetracker.model.Otp;
import com.abel.expensetracker.repository.OtpRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class OtpService {
    private final OtpRepository otpRepository;
    private final SecureRandom random = new SecureRandom();

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public String generateOTP() {
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    public void saveOTP(String userId, String otpCode) {
        Otp otp = new Otp();
        otp.setUserId(userId);
        otp.setCode(otpCode);
        otp.setExpiresAt(Instant.now().plus(10, ChronoUnit.MINUTES));
        otp.setUsed(false);
        otpRepository.save(otp);
    }

    public boolean verifyOTP(String userId, String otpCode) {
        Optional<Otp> latest = otpRepository.findTopByUserIdOrderByExpiresAtDesc(userId);
        if (latest.isEmpty()) return false;
        Otp otp = latest.get();
        if (otp.isUsed()) return false;
        if (Instant.now().isAfter(otp.getExpiresAt())) return false;
        boolean match = otp.getCode().equals(otpCode);
        if (match) {
            otp.setUsed(true);
            otpRepository.save(otp);
        }
        return match;
    }
}
