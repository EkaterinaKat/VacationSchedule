package com.katyshevtseva.vacationschedule.backend.repository;

import com.katyshevtseva.vacationschedule.model.Vacation;
import org.springframework.data.repository.CrudRepository;

public interface VacationRepository extends CrudRepository<Vacation, Long> {
}
