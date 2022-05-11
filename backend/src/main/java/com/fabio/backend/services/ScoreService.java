package com.fabio.backend.services;

import com.fabio.backend.dto.MovieDTO;
import com.fabio.backend.dto.ScoreDTO;
import com.fabio.backend.entities.Movie;
import com.fabio.backend.entities.Score;
import com.fabio.backend.entities.User;
import com.fabio.backend.repositories.MovieRepository;
import com.fabio.backend.repositories.ScoreRepository;
import com.fabio.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO scoreDTO){

        User user = userRepository.findByEmail(scoreDTO.getEmail());
        if(user == null){
            user = new User();
            user.setEmail(scoreDTO.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        Movie movie = movieRepository.findById(scoreDTO.getMovieId()).get();

        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(scoreDTO.getScore());

        score = scoreRepository.saveAndFlush(score);

        double sum = 0.0;
        for (Score s: movie.getScores()){
            sum = sum + s.getValue();
        }
        double avg = sum / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());
        movie = movieRepository.save(movie);
        return new MovieDTO(movie);

    }




















}
