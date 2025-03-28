package com.atlantbh.cinebh.rest.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectionDetailsDTO {
    private List<String> cities;
    private List<String> cinemas;
    private List<String> streets;
    private List<String> postcodes;
    private List<String> streetNumbers;
    private List<String> hallNames;
    private List<UUID> hallIds;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Time> projectionTimes;
    private List<UUID> projectionIds;

    public ProjectionDetailsDTO(List<String> cities) {
        this.cities = cities;
        this.cinemas = null;
        this.streets = null;
        this.postcodes = null;
        this.streetNumbers = null;
        this.hallNames = null;
        this.hallIds = null;
        this.startDate = null;
        this.endDate = null;
        this.projectionTimes = null;
        this.projectionIds = null;
    }
}
