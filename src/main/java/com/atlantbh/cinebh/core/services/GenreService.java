package com.atlantbh.cinebh.core.services;

import com.atlantbh.cinebh.core.models.Genre;
import com.atlantbh.cinebh.core.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }
}
