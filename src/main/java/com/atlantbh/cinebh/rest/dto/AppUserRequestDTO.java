package com.atlantbh.cinebh.rest.dto;

import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.core.models.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.atlantbh.cinebh.core.models.enums.UserType.MEMBER;

@Setter
@Getter
@NoArgsConstructor
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

    public AppUserRequestDTO(AppUser appUser) {
        this.firstName = appUser.getFirstName();
        this.lastName = appUser.getLastName();
        this.phone = appUser.getPhone();
        this.city = appUser.getCity();
        this.country = appUser.getCountry();
        this.imageUrl = appUser.getImageUrl();
        this.email = appUser.getEmail();
        this.type = appUser.getType();
    }

    public AppUser toEntity() {
        AppUser appUser = new AppUser();
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setPhone(phone);
        appUser.setCity(city);
        appUser.setCountry(country);
        appUser.setImageUrl(imageUrl);
        appUser.setEmail(email);
        appUser.setPassword(password);
        appUser.setType(MEMBER);
        return appUser;
    }
}