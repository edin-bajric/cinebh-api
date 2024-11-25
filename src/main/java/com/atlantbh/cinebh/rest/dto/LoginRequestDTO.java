package com.atlantbh.cinebh.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "Email is required.")
    private String email;
    @NotBlank(message = "Password is required.")
    private String password;
}
