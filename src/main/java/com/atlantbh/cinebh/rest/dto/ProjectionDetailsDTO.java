package com.atlantbh.cinebh.rest.dto;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectionDetailsDTO {
    private List<String> cities;
    private List<String> cinemas;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Time> projectionTimes;
}
