package com.katyshevtseva.vacationschedule.backend.controller;

import com.katyshevtseva.vacationschedule.backend.exeption.EntityAccessException;
import com.katyshevtseva.vacationschedule.backend.exeption.EntityCreationException;
import com.katyshevtseva.vacationschedule.backend.mapper.VacationMapper;
import com.katyshevtseva.vacationschedule.backend.model.Vacation;
import com.katyshevtseva.vacationschedule.backend.model.dto.VacationDTO;
import com.katyshevtseva.vacationschedule.backend.request.VacationSearchRequest;
import com.katyshevtseva.vacationschedule.backend.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(value = "/search")
    public ResponseEntity<List<VacationDTO>> getVacationsBySearchRequest(@RequestParam VacationSearchRequest request) {
        try {
            List<Vacation> vacations = vacationService.getVacationsBySearchRequest(request);
            List<VacationDTO> vacationDTOs = new ArrayList<>();
            for (Vacation vacation : vacations) {
                vacationDTOs.add(vacationMapper.toDto(vacation));
            }
            return new ResponseEntity<>(vacationDTOs, HttpStatus.OK);
        } catch (EntityAccessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
