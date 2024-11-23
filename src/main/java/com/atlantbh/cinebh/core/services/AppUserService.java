package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                return appUserRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
            }
        };
    }
}
