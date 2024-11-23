package com.atlantbh.cinebh.rest.dto;

import com.atlantbh.cinebh.core.models.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.atlantbh.cinebh.core.models.enums.UserType.MEMBER;

@Getter
@Setter
public class AppUserRequestDTO {
    private String firstName = "";
    private String lastName = "";
    private String phone = "";
    private String city = "";
    private String country = "";
    private String imageUrl = "";

    @Email(message = "Please provide a valid email address.")
    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    private UserType type = MEMBER;
}