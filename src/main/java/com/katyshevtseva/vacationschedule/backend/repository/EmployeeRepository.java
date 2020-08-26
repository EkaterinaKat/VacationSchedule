package com.katyshevtseva.vacationschedule.backend.repository;

import com.katyshevtseva.vacationschedule.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
