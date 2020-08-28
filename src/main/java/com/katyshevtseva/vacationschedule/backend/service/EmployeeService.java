package com.katyshevtseva.vacationschedule.backend.service;

import com.katyshevtseva.vacationschedule.backend.exeption.EntityAccessException;
import com.katyshevtseva.vacationschedule.backend.exeption.EntityCreationException;
import com.katyshevtseva.vacationschedule.backend.exeption.ExceptionMessage;
import com.katyshevtseva.vacationschedule.backend.model.Employee;
import com.katyshevtseva.vacationschedule.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long employeeId) throws EntityAccessException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        employee.orElseThrow(EntityAccessException::new);

        return employee.get();
    }

    @Transactional
    public void createEmployee(Employee employee) throws EntityCreationException {
        Optional<Employee> byLogin = employeeRepository.findByLogin(employee.getLogin());
        if (byLogin.isPresent())
            throw new EntityCreationException(ExceptionMessage.LOGIN_EXISTS);

        Optional<Employee> byPersonnelNumber = employeeRepository.findByPersonnelNumber(employee.getPersonnelNumber());
        if (byPersonnelNumber.isPresent())
            throw new EntityCreationException(ExceptionMessage.PERSONNEL_NUMBER_EXISTS);

        employeeRepository.save(employee);
    }

    @Transactional
    public void editEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

}
