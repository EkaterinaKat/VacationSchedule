package com.katyshevtseva.vacationschedule.backend.repository;

import com.katyshevtseva.vacationschedule.backend.model.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PositionRepository extends CrudRepository<Position, Long> {

    Optional<Position> findByTitle(String title);
}
