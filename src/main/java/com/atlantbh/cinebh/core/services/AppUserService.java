package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.api.impl.mailsender.MailgunSender;
import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.core.repositories.AppUserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordResetCodeService passwordResetCodeService;
    private final MailgunSender mailgunSender;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                return appUserRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
            }
        };
    }

    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
    }

    public void sendEmail(String email) {
        AppUser appUser = findByEmail(email);
        String resetCode = passwordResetCodeService.insert(appUser);
        mailgunSender.send(email, "Your password reset code is: " + resetCode, "Password Reset Code");
    }

    public void validateCode(String email, String code) {
        AppUser appUser = findByEmail(email);
        boolean isCodeValid = passwordResetCodeService.validateCode(appUser, code);
        if (!isCodeValid) {
            throw new IllegalArgumentException("Invalid code");
        }
    }

    @Transactional
    public void resetPassword(String email, String newPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        @NotBlank(message = "Password is required.")
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        String password = passwordEncoder.encode(newPassword);
        appUserRepository.updatePasswordByEmail(email, password);
    }
}
