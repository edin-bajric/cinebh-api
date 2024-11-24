package com.atlantbh.cinebh.rest.dto;

import com.atlantbh.cinebh.core.models.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class AppUserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String type;
    private String city;
    private String country;
    private String imageUrl;

    public AppUserDTO(AppUser appUser) {
        this.id = appUser.getId();
        this.firstName = appUser.getFirstName();
        this.lastName = appUser.getLastName();
        this.email = appUser.getEmail();
        this.phone = appUser.getPhone();
        this.type = appUser.getType().name();
        this.city = appUser.getCity();
        this.country = appUser.getCountry();
        this.imageUrl = appUser.getImageUrl();
    }
}
