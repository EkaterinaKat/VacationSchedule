package com.katyshevtseva.vacationschedule.backend.service;

import com.katyshevtseva.vacationschedule.backend.exeption.EntityCreationException;
import com.katyshevtseva.vacationschedule.backend.exeption.ExceptionMessage;
import com.katyshevtseva.vacationschedule.backend.model.Vacation;
import com.katyshevtseva.vacationschedule.backend.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class VacationService {
    @Autowired
    private VacationRepository vacationRepository;

    @Transactional
    public void addVacation(Vacation vacation) throws EntityCreationException {
        if (!periodIsCorrect(vacation.getStartDate(), vacation.getExpirationDate()))
            throw new EntityCreationException(ExceptionMessage.INCORRECT_DATES);

        if (newVacationOverlapsWithExistingOne(vacation))
            throw new EntityCreationException(ExceptionMessage.VACATIONS_OVERLAP);

        vacationRepository.save(vacation);
    }

    private boolean periodIsCorrect(Date start, Date end) {
        return start.before(end) && !start.equals(end);
    }

    private boolean newVacationOverlapsWithExistingOne(Vacation newVacation) {
        List<Vacation> existingVacations = vacationRepository.findByEmployeeId(newVacation.getEmployee().getId());

        for (Vacation existingVacation : existingVacations) {
            if (vacationsOverlap(newVacation, existingVacation)) {
                return true;
            }
        }
        return false;
    }

    private boolean vacationsOverlap(Vacation vacation1, Vacation vacation2) {
        Date vacation1start = vacation1.getStartDate();
        Date vacation1end = vacation1.getExpirationDate();
        Date vacation2start = vacation2.getStartDate();
        Date vacation2end = vacation2.getExpirationDate();
        return (vacation1start.after(vacation2start) && vacation1start.before(vacation2end)) ||
                (vacation1end.after(vacation2start) && vacation1end.before(vacation2end));
    }
}
