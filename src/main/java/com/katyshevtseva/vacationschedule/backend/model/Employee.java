package com.katyshevtseva.vacationschedule.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Temporal(TemporalType.DATE)
    private Date dateOfEntry;

    private String login;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Vacation> vacations;
}
