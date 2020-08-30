package com.katyshevtseva.vacationschedule.backend.model.dto;

import com.katyshevtseva.vacationschedule.backend.model.Position;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDTO {

    private long id;

    private String fullName;

    private Date birthDate;

    private long personnelNumber;

    private Position position;

    private Date dateOfEntry;

    private String login;

    private String password;
}
