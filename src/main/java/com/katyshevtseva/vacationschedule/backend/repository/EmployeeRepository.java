package com.katyshevtseva.vacationschedule.backend.repository;

import com.katyshevtseva.vacationschedule.backend.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Optional<Employee> findByLogin(String login);

    Optional<Employee> findByPersonnelNumber(long personnelNumber);
}
