package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.models.Movie;
import com.atlantbh.cinebh.core.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    private Pageable createPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    @GetMapping("/")
    public ResponseEntity<Page<Movie>> getMovies(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "4") int size) {
        return ResponseEntity.ok(movieService.getMovies(createPageable(page, size)));
    }

    @GetMapping("/currently-showing")
    public ResponseEntity<Page<Movie>> currentlyShowing(@RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name = "size", defaultValue = "4") int size) {
        return ResponseEntity.ok(movieService.getCurrentlyShowing(createPageable(page, size)));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<Page<Movie>> upcomingMovies(@RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "4") int size) {
        return ResponseEntity.ok(movieService.getUpcomingMovies(createPageable(page, size)));
    }
}
