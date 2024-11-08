package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.models.Movie;
import com.atlantbh.cinebh.core.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "4";

    private Pageable createPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    @GetMapping("/")
    public ResponseEntity<Page<Movie>> getMovies(@RequestParam(name = "page", defaultValue = DEFAULT_PAGE) int page,
                                                 @RequestParam(name = "size", defaultValue = DEFAULT_SIZE) int size) {
        return ResponseEntity.ok(movieService.getMovies(createPageable(page, size)));
    }

    @GetMapping("/currently-showing")
    public ResponseEntity<Page<Movie>> currentlyShowing(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "cinema", required = false) String cinema,
            @RequestParam(name = "genres", required = false) List<String> genres,
            @RequestParam(name = "projectionTime", required = false) String projectionTime,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(name = "size", defaultValue = DEFAULT_SIZE) int size) {

        Pageable pageable = createPageable(page, size);
        return ResponseEntity.ok(
                movieService.getFilteredCurrentlyShowing(title, city, cinema, genres, projectionTime, startDate, endDate, pageable)
        );
    }

    @GetMapping("/upcoming")
    public ResponseEntity<Page<Movie>> upcomingMovies(@RequestParam(name = "page", defaultValue = DEFAULT_PAGE) int page,
                                                      @RequestParam(name = "size", defaultValue = DEFAULT_SIZE) int size) {
        return ResponseEntity.ok(movieService.getUpcomingMovies(createPageable(page, size)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/featured")
    public ResponseEntity<List<Movie>> featured() {
        return ResponseEntity.ok(movieService.getFeatured());
    }
}
