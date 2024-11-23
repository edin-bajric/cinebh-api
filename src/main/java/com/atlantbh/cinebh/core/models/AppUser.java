package com.atlantbh.cinebh.core.models;

import com.atlantbh.cinebh.core.models.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(length = 45)
    private String firstName;
    @Column(length = 45)
    private String lastName;
    @Column(nullable = false, length = 45, unique = true)
    private String email;
    @Column(length = 45, unique = true)
    private String phone;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private UserType type;
    @Column(length=45)
    private String city;
    @Column(length=45)
    private String country;
    @Column(length=255)
    private String imageUrl;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    private List<PasswordResetCode> passwordResetCodes;
}
