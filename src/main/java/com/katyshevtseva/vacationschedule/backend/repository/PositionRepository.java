package com.katyshevtseva.vacationschedule.backend.repository;

import com.katyshevtseva.vacationschedule.model.Position;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Long> {
}
