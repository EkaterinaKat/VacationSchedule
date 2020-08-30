package com.katyshevtseva.vacationschedule.backend.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VacationDTO {

    private long id;

    private EmployeeDTO employeeDTO;

    private Date startDate;

    private Date expirationDate;
}
