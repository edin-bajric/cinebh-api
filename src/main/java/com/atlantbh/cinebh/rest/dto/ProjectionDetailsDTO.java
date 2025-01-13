package com.atlantbh.cinebh.rest.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
    private List<String> streets;
    private List<String> postcodes;
    private List<String> streetNumbers;
    private List<String> hallNames;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Time> projectionTimes;
}
