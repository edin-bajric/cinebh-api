package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.services.AuthService;
import com.atlantbh.cinebh.core.services.JwtService;
import com.atlantbh.cinebh.rest.dto.AppUserRequestDTO;
import com.atlantbh.cinebh.rest.dto.LoginDTO;
import com.atlantbh.cinebh.rest.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/sign-up")
    public ResponseEntity<LoginDTO> signUp(@RequestBody AppUserRequestDTO appUserRequestDTO) {
        return ResponseEntity.ok(authService.signUp(appUserRequestDTO));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginDTO> signIn(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.signIn(loginRequestDTO));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.replace("Bearer ", "");
        jwtService.blacklistToken(jwtToken);
        return ResponseEntity.ok().build();
    }
}
