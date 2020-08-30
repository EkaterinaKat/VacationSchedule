package com.katyshevtseva.vacationschedule.backend.mapper;

import com.katyshevtseva.vacationschedule.backend.model.Employee;
import com.katyshevtseva.vacationschedule.backend.model.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmployeeMapper {
    @Autowired
    private ModelMapper mapper;

    public Employee toEntity(EmployeeDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Employee.class);
    }

    public EmployeeDTO toDto(Employee entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, EmployeeDTO.class);
    }
}
