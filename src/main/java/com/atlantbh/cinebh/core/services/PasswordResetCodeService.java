package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.core.models.PasswordResetCode;
import com.atlantbh.cinebh.core.repositories.PasswordResetCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PasswordResetCodeService {
    private final PasswordResetCodeRepository passwordResetCodeRepository;

    private static String generateResetCode() {
        int randomCode = 1000 + (int) (Math.random() * 9000);
        return String.valueOf(randomCode);
    }

    public String insert(AppUser appUser) {
        String resetCode = generateResetCode();
        PasswordResetCode passwordResetCode = new PasswordResetCode();
        passwordResetCode.setAppUser(appUser);
        passwordResetCode.setCode(resetCode);
        passwordResetCodeRepository.save(passwordResetCode);
        return resetCode;
    }

    @Transactional
    public boolean validateCode(AppUser appUser, String code) {
        return passwordResetCodeRepository.findByAppUserAndCodeAndUsed(appUser, code, false)
                .map(passwordResetCode -> {
                    passwordResetCodeRepository.updateUsedByAppUserAndCode(appUser, code, true);
                    return true;
                })
                .orElse(false);
    }
}
