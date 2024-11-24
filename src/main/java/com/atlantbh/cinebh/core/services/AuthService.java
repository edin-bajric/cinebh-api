package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.exceptions.auth.UserAlreadyExistsException;
import com.atlantbh.cinebh.core.exceptions.repository.ResourceNotFoundException;
import com.atlantbh.cinebh.core.repositories.AppUserRepository;
import com.atlantbh.cinebh.rest.dto.AppUserDTO;
import com.atlantbh.cinebh.rest.dto.AppUserRequestDTO;
import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.rest.dto.LoginDTO;
import com.atlantbh.cinebh.rest.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AppUserDTO signUp(AppUserRequestDTO appUserRequestDTO) {
        appUserRequestDTO.setPassword(
                passwordEncoder.encode(appUserRequestDTO.getPassword())
        );

        if (appUserRepository.existsByEmail(appUserRequestDTO.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }
        AppUser appUser = appUserRepository.save(appUserRequestDTO.toEntity());

        return new AppUserDTO(appUser);
    }

    public LoginDTO signIn(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
        AppUser appUser = appUserRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("This user does not exist."));

        String jwt = jwtService.generateToken(appUser);

        return new LoginDTO(jwt);
    }
}