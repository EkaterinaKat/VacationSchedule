package com.katyshevtseva.vacationschedule.model.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

    private long id;

    private String fullName;

    private String birthDate;

    private long personnelNumber;

    private long positionId;

    private String dateOfEntry;

    private String login;

    private String password;
}
