package com.atlantbh.cinebh.core.spec;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import com.atlantbh.cinebh.core.models.Movie;

import java.time.LocalDate;
import java.util.List;

public class MovieSpecification {

    public static Specification<Movie> filterCurrentlyShowing(String title, String city, String cinema, List<String> genres,
                                                              String projectionTime, LocalDate date) {
        return Specification.where(hasTitle(title))
                .and(hasCity(city))
                .and(hasCinema(cinema))
                .and(hasGenres(genres))
                .and(hasProjectionTime(projectionTime))
                .and(isShowingOnDate(date));
    }

    public static Specification<Movie> filterUpcomingMovies(String title, String city, String cinema, List<String> genres,
                                                            LocalDate startDate, LocalDate endDate) {
        return Specification.where(hasTitle(title))
                .and(hasCity(city))
                .and(hasCinema(cinema))
                .and(hasGenres(genres))
                .and(isUpcomingBetween(startDate, endDate));
    }

    private static Specification<Movie> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (isNullOrBlank(title)) {
                return criteriaBuilder.conjunction();
            }

            String normalizedQuery = normalizeAndRemoveAccents(title);

            Expression<String> normalizedTitle = criteriaBuilder.function(
                    "TRANSLATE", String.class,
                    criteriaBuilder.lower(root.get("title")),
                    criteriaBuilder.literal("àáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿāăąçćĉċčďđēĕėęěĝğġģĥħīĭįıĵķĺļľŀłńņňŋōŏőœŕŗřśŝşšţťŧūŭůűųŵŷźżž"),
                    criteriaBuilder.literal("aaaaaaaeceeeeiiiidnoooooouuuuyyaaacccccddeeeeeegggghhiiiijkkllllnnnnooooorrrssssstttuuuuuwyyzzz")
            );

            return criteriaBuilder.like(
                    normalizedTitle,
                    "%" + normalizedQuery + "%"
            );
        };
    }

    private static boolean isNullOrBlank(String text) {
        return text == null || text.isBlank();
    }

    private static String normalizeAndRemoveAccents(String text) {
        if (text == null) {
            return null;
        }
        String normalized = java.text.Normalizer.normalize(text, java.text.Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").replaceAll("\\s+", " ").trim().toLowerCase();
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

    private static Specification<Movie> isShowingOnDate(LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(
                    criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), date),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), date)
            );
        };
    }

    private static Specification<Movie> isUpcomingBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null || endDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate),
                    criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), endDate)
            );
        };
    }
}
