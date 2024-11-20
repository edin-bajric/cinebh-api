package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.ProjectionTime;
import com.atlantbh.cinebh.core.repositories.ProjectionTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectionTimeService {
    private final ProjectionTimeRepository projectionTimeRepository;

    public List<ProjectionTime> getProjectionTimes() {
        return projectionTimeRepository.findAll();
    }
}
