package com.katyshevtseva.vacationschedule.backend.service;

import com.katyshevtseva.vacationschedule.backend.exeption.EntityAccessException;
import com.katyshevtseva.vacationschedule.backend.exeption.EntityCreationException;
import com.katyshevtseva.vacationschedule.backend.exeption.ExceptionMessage;
import com.katyshevtseva.vacationschedule.backend.model.Vacation;
import com.katyshevtseva.vacationschedule.backend.repository.VacationRepository;
import com.katyshevtseva.vacationschedule.backend.request.VacationSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private boolean periodIsCorrect(Date beginning, Date end) {
        return beginning.before(end) && !beginning.equals(end);
    }

    private boolean newVacationOverlapsWithExistingOne(Vacation newVacation) {
        List<Vacation> existingVacations = vacationRepository.findByEmployeeId(newVacation.getEmployee().getId());

        for (Vacation existingVacation : existingVacations) {
            if (periodsOverlap(newVacation.getStartDate(), newVacation.getExpirationDate(),
                    existingVacation.getStartDate(), existingVacation.getExpirationDate())) {
                return true;
            }
        }
        return false;
    }

    private boolean periodsOverlap(Date period1start, Date period1end, Date period2start, Date period2end) {
        return (period1start.after(period2start) && period1start.before(period2end)) ||
                (period1end.after(period2start) && period1end.before(period2end));
    }

    public List<Vacation> getVacationsBySearchRequest(VacationSearchRequest request) throws EntityAccessException {

        if (!periodIsCorrect(request.getBeginningDate(), request.getEndDate()))
            throw new EntityAccessException(ExceptionMessage.INCORRECT_DATES);

        List<Vacation> allVacations = (List<Vacation>) vacationRepository.findAll();
        List<Vacation> filteredByDate = filerByDate(allVacations, request.getBeginningDate(), request.getEndDate());
        List<Vacation> filteredByDateAndEmployee = filterByEmployeeId(filteredByDate, request.getEmployeeIds());

        return filteredByDateAndEmployee;
    }

    private List<Vacation> filterByEmployeeId(List<Vacation> vacations, List<Long> ids) {
        List<Vacation> resultList = new ArrayList<>();

        for (Vacation vacation : vacations) {
            for (Long id : ids) {
                if (vacation.getEmployee().getId() == id)
                    resultList.add(vacation);
            }
        }

        return resultList;
    }

    private List<Vacation> filerByDate(List<Vacation> vacations, Date beginningDate, Date endDate) {
        List<Vacation> resultList = new ArrayList<>(vacations);

        if (beginningDate != null) {
            for (Vacation vacation : vacations) {
                if (vacation.getExpirationDate().before(beginningDate))
                    resultList.remove(vacation);
            }
        }

        if (endDate != null) {
            for (Vacation vacation : vacations) {
                if (vacation.getStartDate().after(endDate))
                    resultList.remove(vacation);
            }
        }

        return resultList;
    }


}
