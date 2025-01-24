package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.services.ProjectionService;
import com.atlantbh.cinebh.rest.dto.ProjectionDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/projections")
@RequiredArgsConstructor
public class ProjectionController {
    private final ProjectionService projectionService;

    @GetMapping("/details/{movieId}")
    public ResponseEntity<ProjectionDetailsDTO> getProjectionDetails(@PathVariable("movieId") UUID movieId) {
        return ResponseEntity.ok(projectionService.getProjectionDetails(movieId));
    }
}
