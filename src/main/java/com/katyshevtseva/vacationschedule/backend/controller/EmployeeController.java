package com.katyshevtseva.vacationschedule.backend.controller;

import com.katyshevtseva.vacationschedule.backend.mapper.EmployeeMapper;
import com.katyshevtseva.vacationschedule.backend.service.EmployeeService;
import com.katyshevtseva.vacationschedule.model.Employee;
import com.katyshevtseva.vacationschedule.model.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDTO>> showCatalogue() {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employeeService.getEmployees())
            employeeDTOs.add(employeeMapper.toDto(employee));

        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/create_employee", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = employeeMapper.toEntity(employeeDTO);
        employeeService.createEmployee(employee);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/edit_employee", method = RequestMethod.PUT)
    public ResponseEntity<?> editEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = employeeMapper.toEntity(employeeDTO);
        employeeService.editEmployee(employee);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
