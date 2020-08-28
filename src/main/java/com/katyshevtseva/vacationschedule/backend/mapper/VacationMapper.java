package com.katyshevtseva.vacationschedule.backend.mapper;

import com.katyshevtseva.vacationschedule.backend.model.Vacation;
import com.katyshevtseva.vacationschedule.backend.model.dto.VacationDTO;
import com.katyshevtseva.vacationschedule.backend.repository.EmployeeRepository;
import com.katyshevtseva.vacationschedule.backend.util.DateUtil;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class VacationMapper {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Vacation toEntity(VacationDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Vacation.class);
    }

    public VacationDTO toDto(Vacation entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, VacationDTO.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Vacation.class, VacationDTO.class)
                .addMappings(m -> m.skip(VacationDTO::setEmployeeId))
                .addMappings(m -> m.skip(VacationDTO::setStartDate))
                .addMappings(m -> m.skip(VacationDTO::setExpirationDate))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(VacationDTO.class, Vacation.class)
                .addMappings(m -> m.skip(Vacation::setEmployee))
                .addMappings(m -> m.skip(Vacation::setStartDate))
                .addMappings(m -> m.skip(Vacation::setExpirationDate))
                .setPostConverter(toEntityConverter());
    }

    private Converter<Vacation, VacationDTO> toDtoConverter() {
        return context -> {
            Vacation source = context.getSource();
            VacationDTO destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private Converter<VacationDTO, Vacation> toEntityConverter() {
        return context -> {
            VacationDTO source = context.getSource();
            Vacation destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(Vacation source, VacationDTO destination) {
        destination.setEmployeeId(source.getId());
        destination.setStartDate(DateUtil.dateToString(source.getStartDate()));
        destination.setExpirationDate(DateUtil.dateToString(source.getExpirationDate()));
    }

    private void mapSpecificFields(VacationDTO source, Vacation destination) {
        destination.setEmployee(employeeRepository.findById(source.getId()).orElse(null));
        destination.setStartDate(DateUtil.stringToDate(source.getStartDate()));
        destination.setExpirationDate(DateUtil.stringToDate(source.getExpirationDate()));
    }
}
