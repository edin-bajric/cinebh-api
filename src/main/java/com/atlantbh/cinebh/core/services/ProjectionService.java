package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.Projection;
import com.atlantbh.cinebh.core.repositories.ProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectionService {
    private final ProjectionRepository projectionRepository;

    public Page<Projection> searchProjections(String keyword, Pageable pageable) {
        return projectionRepository.findByMovieTitleContainingIgnoreCase(keyword, pageable);
    }
}
