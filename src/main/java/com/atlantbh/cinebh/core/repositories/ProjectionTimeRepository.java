package com.atlantbh.cinebh.core.repositories;

import com.atlantbh.cinebh.core.models.ProjectionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectionTimeRepository extends JpaRepository<ProjectionTime, UUID> {
}