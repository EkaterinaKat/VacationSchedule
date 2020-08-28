package com.katyshevtseva.vacationschedule.backend.service;

import com.katyshevtseva.vacationschedule.backend.model.Vacation;
import com.katyshevtseva.vacationschedule.backend.repository.VacationRepository;
import com.katyshevtseva.vacationschedule.backend.request.VacationsSortByPeriodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class VacationService {
    @Autowired
    private VacationRepository vacationRepository;

    @Transactional
    public void addVacation(Vacation vacation) {
        vacationRepository.save(vacation);
    }

    public List<Vacation> getVacationsByEmployeeId(long employeeId) {
        return vacationRepository.findByEmployeeId(employeeId);
    }

    public List<Vacation> getVacationsByPeriod(VacationsSortByPeriodRequest sortRequest) {
        return null;
    }

    public List<Vacation> getVacationsSortedByBeginningDate() {
        List<Vacation> vacations = (List<Vacation>) vacationRepository.findAll();
        vacations.sort(Comparator.comparing(Vacation::getStartDate));
        return vacations;
    }

}
