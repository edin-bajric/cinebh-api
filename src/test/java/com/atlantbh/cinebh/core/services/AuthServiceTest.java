package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.exceptions.auth.UserAlreadyExistsException;
import com.atlantbh.cinebh.core.exceptions.repository.ResourceNotFoundException;
import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.core.repositories.AppUserRepository;
import com.atlantbh.cinebh.rest.dto.AppUserRequestDTO;
import com.atlantbh.cinebh.rest.dto.LoginDTO;
import com.atlantbh.cinebh.rest.dto.LoginRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

class AuthServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    AuthServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signUpShouldCreateUserAndReturnJwt() {
        AppUserRequestDTO requestDTO = new AppUserRequestDTO();
        requestDTO.setEmail("test@example.com");
        requestDTO.setPassword("password");

        when(appUserRepository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(requestDTO.getPassword())).thenReturn("encodedPassword");
        when(appUserRepository.save(any(AppUser.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(jwtService.generateToken(any(AppUser.class))).thenReturn("jwtToken");

        LoginDTO loginDTO = authService.signUp(requestDTO);

        assertNotNull(loginDTO);
        assertEquals("jwtToken", loginDTO.getJwt());
        verify(appUserRepository, times(1)).save(any(AppUser.class));
    }

    @Test
    void signUpShouldThrowExceptionWhenUserAlreadyExists() {
        AppUserRequestDTO requestDTO = new AppUserRequestDTO();
        requestDTO.setEmail("test@example.com");

        when(appUserRepository.existsByEmail(requestDTO.getEmail())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> authService.signUp(requestDTO));
        verify(appUserRepository, never()).save(any(AppUser.class));
    }

    @Test
    void signInShouldAuthenticateAndReturnJwt() {
        LoginRequestDTO requestDTO = new LoginRequestDTO("test@example.com", "password");
        AppUser appUser = new AppUser();
        appUser.setId(UUID.randomUUID());

        when(appUserRepository.findByEmail(requestDTO.getEmail())).thenReturn(Optional.of(appUser));
        when(jwtService.generateToken(appUser)).thenReturn("jwtToken");

        LoginDTO loginDTO = authService.signIn(requestDTO);

        assertNotNull(loginDTO);
        assertEquals("jwtToken", loginDTO.getJwt());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void signInShouldThrowExceptionWhenUserNotFound() {
        LoginRequestDTO requestDTO = new LoginRequestDTO("test@example.com", "password");

        when(appUserRepository.findByEmail(requestDTO.getEmail())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authService.signIn(requestDTO));
    }
}

