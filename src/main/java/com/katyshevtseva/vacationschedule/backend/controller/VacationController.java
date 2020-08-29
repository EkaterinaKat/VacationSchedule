package com.katyshevtseva.vacationschedule.backend.controller;

import com.katyshevtseva.vacationschedule.backend.exeption.EntityCreationException;
import com.katyshevtseva.vacationschedule.backend.mapper.VacationMapper;
import com.katyshevtseva.vacationschedule.backend.model.Vacation;
import com.katyshevtseva.vacationschedule.backend.model.dto.VacationDTO;
import com.katyshevtseva.vacationschedule.backend.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/vacation")
@RestController
public class VacationController {
    @Autowired
    private VacationService vacationService;
    @Autowired
    private VacationMapper vacationMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addVacation(@RequestBody VacationDTO vacationDTO) {
        Vacation vacation = vacationMapper.toEntity(vacationDTO);

        try {
            vacationService.addVacation(vacation);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityCreationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
