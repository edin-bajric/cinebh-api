package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.Venue;
import com.atlantbh.cinebh.core.repositories.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {
    private final VenueRepository venueRepository;

    public Page<Venue> getVenues(Pageable pageable) {
        return venueRepository.findAll(pageable);
    }

    public List<Venue> getVenues() {
        return venueRepository.findAll();
    }
}
