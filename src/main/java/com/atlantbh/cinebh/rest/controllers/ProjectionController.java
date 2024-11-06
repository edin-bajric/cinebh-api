package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.models.Projection;
import com.atlantbh.cinebh.core.services.ProjectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/projections")
@RequiredArgsConstructor
public class ProjectionController {
    private final ProjectionService projectionService;
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "4";

    private Pageable createPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Projection>> searchProjectionsByMovieTitle(@RequestParam(name = "page", defaultValue = DEFAULT_PAGE) int page,
                                                                          @RequestParam(name = "size", defaultValue = DEFAULT_SIZE) int size,
                                                                          @RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(projectionService.searchProjections(keyword, createPageable(page, size)));
    }
}
