package com.atlantbh.cinebh.core.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PasswordResetCode {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUserId")
    private AppUser appUser;
    @Column(nullable = false, length = 4)
    private String code;
    @Column(nullable = false)
    private boolean used = false;

    public static String generateResetCode() {
        int randomCode = 1000 + (int) (Math.random() * 9000);
        return String.valueOf(randomCode);
    }
}
