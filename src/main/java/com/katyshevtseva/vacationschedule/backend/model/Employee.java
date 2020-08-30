package com.katyshevtseva.vacationschedule.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private long personnelNumber;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Temporal(TemporalType.DATE)
    private Date dateOfEntry;

    private String login;

    private String password;
}
