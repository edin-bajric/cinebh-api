package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.core.models.PasswordResetCode;
import com.atlantbh.cinebh.core.repositories.PasswordResetCodeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PasswordResetCodeServiceTest {

    @Mock
    private PasswordResetCodeRepository passwordResetCodeRepository;

    @InjectMocks
    private PasswordResetCodeService passwordResetCodeService;

    public PasswordResetCodeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveResetCode() {
        AppUser appUser = new AppUser();
        appUser.setId(UUID.randomUUID());

        when(passwordResetCodeRepository.save(any(PasswordResetCode.class))).thenReturn(new PasswordResetCode());

        String result = passwordResetCodeService.insert(appUser);

        assertNotNull(result);
        verify(passwordResetCodeRepository, times(1)).save(any(PasswordResetCode.class));
    }


    @Test
    void shouldReturnTrueWhenCodeIsValid() {
        AppUser appUser = new AppUser();
        String code = "1234";
        PasswordResetCode passwordResetCode = new PasswordResetCode();
        passwordResetCode.setAppUser(appUser);
        passwordResetCode.setCode(code);

        when(passwordResetCodeRepository.findByAppUserAndCodeAndUsed(appUser, code, false))
                .thenReturn(Optional.of(passwordResetCode));

        boolean result = passwordResetCodeService.validateCode(appUser, code);

        assertTrue(result);
        verify(passwordResetCodeRepository, times(1))
                .updateUsedByAppUserAndCode(appUser, code, true);
    }

    @Test
    void shouldReturnFalseWhenCodeIsInvalid() {
        AppUser appUser = new AppUser();
        String code = "1234";

        when(passwordResetCodeRepository.findByAppUserAndCodeAndUsed(appUser, code, false))
                .thenReturn(Optional.empty());

        boolean result = passwordResetCodeService.validateCode(appUser, code);

        assertFalse(result);
        verify(passwordResetCodeRepository, never()).updateUsedByAppUserAndCode(any(), any(), anyBoolean());
    }
}
