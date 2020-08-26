package com.katyshevtseva.vacationschedule.backend.service;

import com.katyshevtseva.vacationschedule.backend.repository.EmployeeRepository;
import com.katyshevtseva.vacationschedule.backend.repository.PositionRepository;
import com.katyshevtseva.vacationschedule.backend.repository.VacationRepository;
import com.katyshevtseva.vacationschedule.model.Employee;
import com.katyshevtseva.vacationschedule.model.Position;
import com.katyshevtseva.vacationschedule.model.Vacation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private VacationRepository vacationRepository;
    private PositionRepository positionRepository; //todo возможно это здесь не нужно

    public void meth() {
        Position position = new Position();
        position.setTitle("boss");
        positionRepository.save(position);

        Employee employee = (new Employee.Builder())
                .setFullName("oleg")
                .setBirthDate(new Date())
                .setPersonnelNumber(6L)
                .setPositon(position)
                .setDateOfEntry(new Date())
                .setLogin("art")
                .setPassword("lj")
                .get();
        employeeRepository.save(employee);

        Vacation vacation = new Vacation();
        vacation.setStartDate(new Date());
        vacation.setExpirationDate(new Date());
        vacation.setEmployee(employee);
        vacationRepository.save(vacation);

        printInfo();

        vacationRepository.delete(vacation);

        printInfo();

        employeeRepository.delete(employee);

        printInfo();

    }

    private void printInfo() {
        System.out.println("*************************************************************************");

        for (Position position1 : positionRepository.findAll()) {
            System.out.println(position1);
        }

        for (Employee employee1 : employeeRepository.findAll()) {
            System.out.println(employee1);
        }

        for (Vacation vacation1 : vacationRepository.findAll()) {
            System.out.println(vacation1);
        }
    }
}
