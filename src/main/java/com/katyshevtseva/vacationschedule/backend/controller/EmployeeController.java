package com.katyshevtseva.vacationschedule.backend.controller;

import com.katyshevtseva.vacationschedule.backend.exeption.EntityAccessException;
import com.katyshevtseva.vacationschedule.backend.exeption.EntityCreationException;
import com.katyshevtseva.vacationschedule.backend.mapper.EmployeeMapper;
import com.katyshevtseva.vacationschedule.backend.model.Employee;
import com.katyshevtseva.vacationschedule.backend.model.dto.EmployeeDTO;
import com.katyshevtseva.vacationschedule.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping(value = "/catalogue")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employeeService.getEmployees())
            employeeDTOs.add(employeeMapper.toDto(employee));

        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "id") long employeeId) {
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            return new ResponseEntity<>(employeeMapper.toDto(employee), HttpStatus.OK);
        } catch (EntityAccessException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = employeeMapper.toEntity(employeeDTO);
        try {
            employeeService.createEmployee(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityCreationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<?> editEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = employeeMapper.toEntity(employeeDTO);
        employeeService.editEmployee(employee);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
