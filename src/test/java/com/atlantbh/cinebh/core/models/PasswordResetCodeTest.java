package com.atlantbh.cinebh.core.models;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordResetCodeTest {

    @Test
    void shouldSetAndGetCorrectly() {
        UUID id = UUID.randomUUID();
        AppUser appUser = new AppUser();
        String code = "1234";
        boolean used = true;

        PasswordResetCode passwordResetCode = new PasswordResetCode();
        passwordResetCode.setId(id);
        passwordResetCode.setAppUser(appUser);
        passwordResetCode.setCode(code);
        passwordResetCode.setUsed(used);

        assertEquals(id, passwordResetCode.getId());
        assertEquals(appUser, passwordResetCode.getAppUser());
        assertEquals(code, passwordResetCode.getCode());
        assertTrue(passwordResetCode.isUsed());
    }
}
