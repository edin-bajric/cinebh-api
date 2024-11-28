package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.api.impl.mailsender.MailgunSender;
import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.core.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
