package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.services.AuthService;
import com.atlantbh.cinebh.rest.dto.AppUserDTO;
import com.atlantbh.cinebh.rest.dto.AppUserRequestDTO;
import com.atlantbh.cinebh.rest.dto.LoginDTO;
import com.atlantbh.cinebh.rest.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<AppUserDTO> signUp(@RequestBody AppUserRequestDTO appUserRequestDTO) {
        return ResponseEntity.ok(authService.signUp(appUserRequestDTO));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginDTO> signIn(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.signIn(loginRequestDTO));
    }
}
