package com.atlantbh.cinebh.core.models;

import com.atlantbh.cinebh.core.models.enums.UserType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AppUserTest {

    @Test
    void shouldSetAndGetCorrectly() {
        UUID id = UUID.randomUUID();
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phone = "123-456-7890";
        String password = "securePassword";
        UserType type = UserType.MEMBER;
        String city = "Sarajevo";
        String country = "Bosnia";
        String imageUrl = "https://example.com/image.jpg";

        AppUser appUser = new AppUser();
        appUser.setId(id);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setEmail(email);
        appUser.setPhone(phone);
        appUser.setPassword(password);
        appUser.setType(type);
        appUser.setCity(city);
        appUser.setCountry(country);
        appUser.setImageUrl(imageUrl);

        assertEquals(id, appUser.getId());
        assertEquals(firstName, appUser.getFirstName());
        assertEquals(lastName, appUser.getLastName());
        assertEquals(email, appUser.getEmail());
        assertEquals(phone, appUser.getPhone());
        assertEquals(password, appUser.getPassword());
        assertEquals(type, appUser.getType());
        assertEquals(city, appUser.getCity());
        assertEquals(country, appUser.getCountry());
        assertEquals(imageUrl, appUser.getImageUrl());
    }

    @Test
    void shouldReturnCorrectAuthority() {
        AppUser appUser = new AppUser();
        appUser.setType(UserType.MEMBER);

        assertNotNull(appUser.getAuthorities());
        assertEquals(1, appUser.getAuthorities().size());
        assertEquals("MEMBER", appUser.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void relationshipWithPasswordResetCodesShouldWorkCorrectly() {
        AppUser appUser = new AppUser();
        PasswordResetCode code1 = new PasswordResetCode();
        PasswordResetCode code2 = new PasswordResetCode();
        appUser.setPasswordResetCodes(List.of(code1, code2));

        assertNotNull(appUser.getPasswordResetCodes());
        assertEquals(2, appUser.getPasswordResetCodes().size());
    }

    @Test
    void userDetailsInterfaceMethodsShouldReturnExpectedValues() {
        AppUser appUser = new AppUser();
        appUser.setEmail("john.doe@example.com");

        assertEquals("john.doe@example.com", appUser.getUsername());
        assertTrue(appUser.isAccountNonExpired());
        assertTrue(appUser.isAccountNonLocked());
        assertTrue(appUser.isCredentialsNonExpired());
        assertTrue(appUser.isEnabled());
    }
}

