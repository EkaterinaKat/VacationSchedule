package com.katyshevtseva.vacationschedule.backend.model.dto;

import lombok.Data;

@Data
public class VacationDTO {

    private long id;

    private long employeeId;

    private String startDate;

    private String expirationDate;
}
