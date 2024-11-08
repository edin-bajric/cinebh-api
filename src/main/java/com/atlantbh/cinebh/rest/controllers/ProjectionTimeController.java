package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.models.ProjectionTime;
import com.atlantbh.cinebh.core.services.ProjectionTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/projection-times")
@RequiredArgsConstructor
public class ProjectionTimeController {
    private final ProjectionTimeService projectionTimeService;

    @GetMapping("/")
    public ResponseEntity<List<ProjectionTime>> getProjectionTimes() {
        return ResponseEntity.ok(projectionTimeService.getProjectionTimes());
    }
}
