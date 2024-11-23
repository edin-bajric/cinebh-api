package com.atlantbh.cinebh.rest.dto;

import com.atlantbh.cinebh.core.models.AppUser;
import lombok.Builder;
import lombok.Getter;


import java.util.UUID;

@Getter
@Builder
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

    public static AppUserDTO fromEntity(AppUser appUser) {
        return AppUserDTO.builder()
                .id(appUser.getId())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .phone(appUser.getPhone())
                .type(appUser.getType().name())
                .city(appUser.getCity())
                .country(appUser.getCountry())
                .imageUrl(appUser.getImageUrl())
                .build();
    }
}
