package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.models.Venue;
import com.atlantbh.cinebh.core.services.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    private Pageable createPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    @GetMapping("/")
    public ResponseEntity<Page<Venue>> getVenues(@RequestParam (name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "4") int size) {
        return ResponseEntity.ok(venueService.getVenues(createPageable(page, size)));
    }
}
