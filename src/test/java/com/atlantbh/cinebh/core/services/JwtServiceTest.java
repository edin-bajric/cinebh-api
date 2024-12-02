package com.atlantbh.cinebh.core.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    private UserDetails mockUserDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String secretKey = "mySuperSecretKeyForJwtTesting12345678901234567890";
        jwtService.jwtSigningKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        mockUserDetails = new User("testUser", "password", Collections.emptyList());
    }

    @Test
    void generateTokenShouldReturnValidJwt() {
        String token = jwtService.generateToken(mockUserDetails);

        assertNotNull(token);
        assertDoesNotThrow(() -> jwtService.extractUserName(token));
        assertEquals(mockUserDetails.getUsername(), jwtService.extractUserName(token));
    }

    @Test
    void extractUserNameShouldReturnCorrectUserName() {
        String token = jwtService.generateToken(mockUserDetails);
        String userName = jwtService.extractUserName(token);
        assertEquals(mockUserDetails.getUsername(), userName);
    }

    @Test
    void isTokenValidShouldReturnTrueForValidToken() {
        String token = jwtService.generateToken(mockUserDetails);
        boolean isValid = jwtService.isTokenValid(token, mockUserDetails);
        assertTrue(isValid);
    }

    @Test
    void isTokenValidShouldReturnFalseForBlacklistedToken() {
        String token = jwtService.generateToken(mockUserDetails);
        jwtService.blacklistToken(token);
        boolean isValid = jwtService.isTokenValid(token, mockUserDetails);
        assertFalse(isValid);
    }

    @Test
    void blacklistTokenShouldAddTokenToBlacklist() {
        String token = jwtService.generateToken(mockUserDetails);
        jwtService.blacklistToken(token);
        assertTrue(jwtService.isTokenBlacklisted(token));
    }

    @Test
    void isTokenBlacklistedShouldReturnFalseForNonBlacklistedToken() {
        String token = jwtService.generateToken(mockUserDetails);
        assertFalse(jwtService.isTokenBlacklisted(token));
    }

    @Test
    void extractExpirationShouldReturnExpirationDate() {
        String token = jwtService.generateToken(mockUserDetails);
        Date expiration = jwtService.extractExpiration(token);
        assertNotNull(expiration);
        assertTrue(expiration.after(new Date()));
    }
}

