package com.katyshevtseva.vacationschedule.backend.model.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

    private long id;

    private String fullName;

    private String birthDate;

    private long personnelNumber;

    private String position;

    private String dateOfEntry;

    private String login;

    private String password;
}
