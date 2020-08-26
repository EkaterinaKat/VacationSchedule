package com.katyshevtseva.vacationschedule.backend.controller;

import com.katyshevtseva.vacationschedule.backend.mapper.VacationMapper;
import com.katyshevtseva.vacationschedule.backend.service.VacationService;
import com.katyshevtseva.vacationschedule.model.Vacation;
import com.katyshevtseva.vacationschedule.model.dto.EmployeeDTO;
import com.katyshevtseva.vacationschedule.model.dto.VacationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/vacations")
@RestController
public class VacationController {
    @Autowired
    private VacationService vacationService;
    @Autowired
    private VacationMapper vacationMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addVacation(@RequestBody VacationDTO vacationDTO) {
        Vacation vacation = vacationMapper.toEntity(vacationDTO);
        vacationService.addVacation(vacation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/employee_vacations", method = RequestMethod.GET)
    public ResponseEntity<List<VacationDTO>> getEmployeeVacations(@RequestParam EmployeeDTO employeeDTO) {
        List<Vacation> vacations = vacationService.getVacationsByEmployeeId(employeeDTO.getId());
        List<VacationDTO> vacationDTOs = new ArrayList<>();

        for (Vacation vacation : vacations)
            vacationDTOs.add(vacationMapper.toDto(vacation));

        return new ResponseEntity<>(vacationDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/sort_by_start_date", method = RequestMethod.GET)
    public ResponseEntity<List<VacationDTO>> getVacationsSortedByBeginingDate() {
        List<VacationDTO> vacationDTOs = new ArrayList<>();

        for (Vacation vacation : vacationService.getVacationsSortedByBeginningDate())
            vacationDTOs.add(vacationMapper.toDto(vacation));

        return new ResponseEntity<>(vacationDTOs, HttpStatus.OK);
    }

}
