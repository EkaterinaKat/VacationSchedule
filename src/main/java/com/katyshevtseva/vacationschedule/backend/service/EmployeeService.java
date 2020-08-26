package com.katyshevtseva.vacationschedule.backend.service;

import com.katyshevtseva.vacationschedule.backend.repository.EmployeeRepository;
import com.katyshevtseva.vacationschedule.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void editEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

}
