package com.atlantbh.cinebh.core.repositories;

import com.atlantbh.cinebh.core.models.AppUser;
import com.atlantbh.cinebh.core.models.PasswordResetCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, UUID> {
    Optional<PasswordResetCode> findByAppUserAndCodeAndUsed(AppUser appUser, String code, boolean used);
    @Modifying
    @Query("UPDATE PasswordResetCode prc SET prc.used = :used WHERE prc.appUser = :appUser AND prc.code = :code")
    void updateUsedByAppUserAndCode(AppUser appUser, String code, boolean used);
}
