package com.katyshevtseva.vacationschedule.backend.mapper;

import com.katyshevtseva.vacationschedule.backend.repository.PositionRepository;
import com.katyshevtseva.vacationschedule.model.Employee;
import com.katyshevtseva.vacationschedule.model.dto.EmployeeDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Component
public class EmployeeMapper {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
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
                .addMappings(m -> m.skip(EmployeeDTO::setPositionId))
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
        destination.setBirthDate(dateFormat.format(source.getBirthDate()));
        destination.setDateOfEntry(dateFormat.format(source.getDateOfEntry()));
        destination.setPositionId(source.getPosition().getId());
    }

    private void mapSpecificFields(EmployeeDTO source, Employee destination) {
        try {
            destination.setBirthDate(dateFormat.parse(source.getBirthDate()));
            destination.setDateOfEntry(dateFormat.parse(source.getDateOfEntry()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        destination.setPosition(positionRepository.findById(source.getPositionId()).orElse(null));
    }
}
