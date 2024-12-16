package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmail(@RequestParam String email) {
        appUserService.sendEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/validate-code")
    public ResponseEntity<Void> validateCode(@RequestParam String email, @RequestParam String code) {
        appUserService.validateCode(email, code);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        appUserService.resetPassword(email, newPassword);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
