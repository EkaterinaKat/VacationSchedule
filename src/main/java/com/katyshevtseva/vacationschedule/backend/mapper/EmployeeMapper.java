package com.katyshevtseva.vacationschedule.backend.mapper;

import com.katyshevtseva.vacationschedule.backend.model.Employee;
import com.katyshevtseva.vacationschedule.backend.model.dto.EmployeeDTO;
import com.katyshevtseva.vacationschedule.backend.repository.PositionRepository;
import com.katyshevtseva.vacationschedule.backend.util.DateUtil;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class EmployeeMapper {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PositionRepository positionRepository;

    public Employee toEntity(EmployeeDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Employee.class);
    }

    public EmployeeDTO toDto(Employee entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, EmployeeDTO.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Employee.class, EmployeeDTO.class)
                .addMappings(m -> m.skip(EmployeeDTO::setBirthDate))
                .addMappings(m -> m.skip(EmployeeDTO::setDateOfEntry))
                .addMappings(m -> m.skip(EmployeeDTO::setPosition))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(EmployeeDTO.class, Employee.class)
                .addMappings(m -> m.skip(Employee::setBirthDate))
                .addMappings(m -> m.skip(Employee::setDateOfEntry))
                .addMappings(m -> m.skip(Employee::setPosition))
                .setPostConverter(toEntityConverter());
    }

    private Converter<Employee, EmployeeDTO> toDtoConverter() {
        return context -> {
            Employee source = context.getSource();
            EmployeeDTO destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private Converter<EmployeeDTO, Employee> toEntityConverter() {
        return context -> {
            EmployeeDTO source = context.getSource();
            Employee destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(Employee source, EmployeeDTO destination) {
        destination.setBirthDate(DateUtil.dateToString(source.getBirthDate()));
        destination.setDateOfEntry(DateUtil.dateToString(source.getDateOfEntry()));
        destination.setPosition(source.getPosition().getTitle());
    }

    private void mapSpecificFields(EmployeeDTO source, Employee destination) {
        destination.setBirthDate(DateUtil.stringToDate(source.getBirthDate()));
        destination.setDateOfEntry(DateUtil.stringToDate(source.getDateOfEntry()));
        destination.setPosition(positionRepository.findByTitle(source.getPosition()).get());
    }
}
