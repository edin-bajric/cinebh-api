package com.atlantbh.cinebh.rest.controllers;

import com.atlantbh.cinebh.core.models.Genre;
import com.atlantbh.cinebh.core.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/")
    public ResponseEntity<List<Genre>> getGenres() {
        return ResponseEntity.ok(genreService.getGenres());
    }
}
