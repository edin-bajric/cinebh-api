package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.api.impl.mailsender.MailgunSender;
import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.core.repositories.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.contains;

class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordResetCodeService passwordResetCodeService;

    @Mock
    private MailgunSender mailgunSender;

    @InjectMocks
    private AppUserService appUserService;

    public AppUserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAppUserWhenEmailExists() {
        String email = "test@example.com";
        AppUser appUser = new AppUser();
        appUser.setEmail(email);

        when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(appUser));

        AppUser result = appUserService.findByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(appUserRepository, times(1)).findByEmail(email);
    }

    @Test
    void shouldThrowExceptionWhenEmailDoesNotExist() {
        String email = "nonexistent@example.com";
        when(appUserRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> appUserService.findByEmail(email));
        verify(appUserRepository, times(1)).findByEmail(email);
    }

    @Test
    void shouldSendEmailWithResetCode() {
        String email = "test@example.com";
        AppUser appUser = new AppUser();
        appUser.setEmail(email);
        String resetCode = "1234";

        when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(appUser));
        when(passwordResetCodeService.insert(appUser)).thenReturn(resetCode);

        appUserService.sendEmail(email);

        verify(mailgunSender, times(1)).send(eq(email), contains(resetCode), eq("Password Reset Code"));
    }

    @Test
    void shouldUpdatePassword() {
        String email = "test@example.com";
        String newPassword = "newSecurePassword";

        doNothing().when(appUserRepository).updatePasswordByEmail(eq(email), anyString());

        appUserService.resetPassword(email, newPassword);

        verify(appUserRepository, times(1)).updatePasswordByEmail(eq(email), anyString());
    }
}