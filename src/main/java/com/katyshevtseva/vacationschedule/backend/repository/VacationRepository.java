package com.katyshevtseva.vacationschedule.backend.repository;

import com.katyshevtseva.vacationschedule.backend.model.Vacation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacationRepository extends CrudRepository<Vacation, Long> {
    List<Vacation> findByEmployeeId(long employeeId);
}
