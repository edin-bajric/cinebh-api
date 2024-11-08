package com.atlantbh.cinebh.core.spec;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import com.atlantbh.cinebh.core.models.Movie;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MovieSpecification {

    public static Specification<Movie> filterCurrentlyShowing(String title, String city, String cinema, List<String> genres,
                                                              String projectionTime, String startDate, String endDate) {
        return Specification.where(hasTitle(title))
                .and(hasCity(city))
                .and(hasCinema(cinema))
                .and(hasGenres(genres))
                .and(hasProjectionTime(projectionTime))
                .and(hasStartDate(startDate))
                .and(hasEndDate(endDate));
    }

    private static Specification<Movie> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                (title == null || title.isEmpty())
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    private static Specification<Movie> hasCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if (city == null || city.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Object, Object> venue = root.join("projections").join("venue");
            return criteriaBuilder.equal(venue.get("city"), city);
        };
    }

    private static Specification<Movie> hasCinema(String cinema) {
        return (root, query, criteriaBuilder) -> {
            if (cinema == null || cinema.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Object, Object> venue = root.join("projections").join("venue");
            return criteriaBuilder.equal(venue.get("name"), cinema);
        };
    }

    private static Specification<Movie> hasGenres(List<String> genres) {
        return (root, query, criteriaBuilder) -> {
            if (genres == null || genres.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.join("genres").get("name").in(genres);
        };
    }

    private static Specification<Movie> hasProjectionTime(String projectionTime) {
        return (root, query, criteriaBuilder) -> {
            if (projectionTime == null || projectionTime.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Object, Object> projection = root.join("projections");
            Join<Object, Object> projectionTimeJoin = projection.join("projectionTimes");
            return criteriaBuilder.equal(projectionTimeJoin.get("time"), java.sql.Time.valueOf(projectionTime));
        };
    }

    private static Specification<Movie> hasStartDate(String startDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null || startDate.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
            return criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), start);
        };
    }

    private static Specification<Movie> hasEndDate(String endDate) {
        return (root, query, criteriaBuilder) -> {
            if (endDate == null || endDate.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
            return criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), end);
        };
    }
}

