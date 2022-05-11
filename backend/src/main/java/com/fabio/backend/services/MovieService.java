package com.fabio.backend.services;

import com.fabio.backend.dto.MovieDTO;
import com.fabio.backend.entities.Movie;
import com.fabio.backend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Page<MovieDTO> findAll(Pageable pageable){
        Page<Movie> page = movieRepository.findAll(pageable);
        return page.map(MovieDTO::new);
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id){
        Movie movie = movieRepository.findById(id).get();
        return new MovieDTO(movie);
    }
}
