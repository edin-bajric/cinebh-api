package com.atlantbh.cinebh.core.repositories;

import com.atlantbh.cinebh.core.models.AppUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(@Email @NotBlank String email);
    @Modifying
    @Query("UPDATE AppUser u SET u.password = :password WHERE u.email = :email")
    void updatePasswordByEmail(@Email @NotBlank String email, String password);
}
