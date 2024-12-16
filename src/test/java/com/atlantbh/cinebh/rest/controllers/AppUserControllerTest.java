package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.services.AppUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

class AppUserControllerTest {

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private AppUserController appUserController;

    public AppUserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendEmailShouldReturnNoContent() {
        String email = "test@example.com";

        doNothing().when(appUserService).sendEmail(email);

        ResponseEntity<Void> response = appUserController.sendEmail(email);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(appUserService, times(1)).sendEmail(email);
    }

    @Test
    void validateCodeShouldReturnNoContent() {
        String email = "test@example.com";
        String code = "1234";

        doNothing().when(appUserService).validateCode(email, code);

        ResponseEntity<Void> response = appUserController.validateCode(email, code);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(appUserService, times(1)).validateCode(email, code);
    }

    @Test
    void resetPasswordShouldReturnNoContent() {
        String email = "test@example.com";
        String newPassword = "newSecurePassword";

        doNothing().when(appUserService).resetPassword(email, newPassword);

        ResponseEntity<Void> response = appUserController.resetPassword(email, newPassword);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(appUserService, times(1)).resetPassword(email, newPassword);
    }
}