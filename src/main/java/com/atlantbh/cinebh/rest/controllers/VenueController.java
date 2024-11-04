package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.models.Venue;
import com.atlantbh.cinebh.core.services.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "4";

    private Pageable createPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    @GetMapping("/")
    public ResponseEntity<Page<Venue>> getVenues(@RequestParam (name = "page", defaultValue = DEFAULT_PAGE) int page,
                                                 @RequestParam(name = "size", defaultValue = DEFAULT_SIZE) int size) {
        return ResponseEntity.ok(venueService.getVenues(createPageable(page, size)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Venue>> getVenues() {
        return ResponseEntity.ok(venueService.getVenues());
    }
}
