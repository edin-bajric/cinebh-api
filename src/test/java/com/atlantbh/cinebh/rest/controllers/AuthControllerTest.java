package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.services.AuthService;
import com.atlantbh.cinebh.core.services.JwtService;
import com.atlantbh.cinebh.rest.dto.AppUserRequestDTO;
import com.atlantbh.cinebh.rest.dto.LoginDTO;
import com.atlantbh.cinebh.rest.dto.LoginRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;


class AuthControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthController authController;

    AuthControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signUpShouldReturnJwt() {
        AppUserRequestDTO requestDTO = new AppUserRequestDTO();
        LoginDTO loginDTO = new LoginDTO("jwtToken");

        when(authService.signUp(requestDTO)).thenReturn(loginDTO);

        ResponseEntity<LoginDTO> response = authController.signUp(requestDTO);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());
        assertEquals("jwtToken", response.getBody().getJwt());
        verify(authService, times(1)).signUp(requestDTO);
    }

    @Test
    void signInShouldReturnJwt() {
        LoginRequestDTO requestDTO = new LoginRequestDTO("test@example.com", "password");
        LoginDTO loginDTO = new LoginDTO("jwtToken");

        when(authService.signIn(requestDTO)).thenReturn(loginDTO);

        ResponseEntity<LoginDTO> response = authController.signIn(requestDTO);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());
        assertEquals("jwtToken", response.getBody().getJwt());
        verify(authService, times(1)).signIn(requestDTO);
    }

    @Test
    void logoutShouldBlacklistTokenAndReturnOk() {
        String token = "Bearer jwtToken";

        doNothing().when(jwtService).blacklistToken("jwtToken");

        ResponseEntity<Void> response = authController.logout(token);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(jwtService, times(1)).blacklistToken("jwtToken");
    }
}

