package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.core.models.PasswordResetCode;
import com.atlantbh.cinebh.core.repositories.PasswordResetCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.atlantbh.cinebh.core.models.PasswordResetCode.generateResetCode;

@Service
@RequiredArgsConstructor
public class PasswordResetCodeService {
    private final PasswordResetCodeRepository passwordResetCodeRepository;

    public String insert(AppUser appUser) {
        String resetCode = generateResetCode();
        PasswordResetCode passwordResetCode = new PasswordResetCode();
        passwordResetCode.setAppUser(appUser);
        passwordResetCode.setCode(resetCode);
        passwordResetCodeRepository.save(passwordResetCode);
        return resetCode;
    }
}
