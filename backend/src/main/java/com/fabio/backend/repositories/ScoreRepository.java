package com.fabio.backend.repositories;

import com.fabio.backend.entities.Score;
import com.fabio.backend.entities.ScorePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScorePk> {
}
