package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.repositories.ProjectionRepository;
import com.atlantbh.cinebh.rest.dto.ProjectionDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectionService {
    private final ProjectionRepository projectionRepository;

    public ProjectionDetailsDTO getProjectionDetails(UUID movieId) {
        return projectionRepository.getProjectionDetails(movieId);
    }
}
