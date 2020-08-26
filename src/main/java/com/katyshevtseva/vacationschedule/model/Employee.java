package com.katyshevtseva.vacationschedule.model;

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

    private Date birthDate;

    private long personnelNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    private Date dateOfEntry;

    private String login;

    private String password;

    public static class Builder {
        private Employee employee = new Employee();

        public Employee.Builder setFullName(String fullName) {
            employee.setFullName(fullName);
            return this;
        }

        public Employee.Builder setBirthDate(Date birthDate) {
            employee.setBirthDate(birthDate);
            return this;
        }

        public Employee.Builder setPersonnelNumber(Long personnelNumber) {
            employee.setPersonnelNumber(personnelNumber);
            return this;
        }

        public Employee.Builder setPositon(Position position) {
            employee.setPosition(position);
            return this;
        }

        public Employee.Builder setDateOfEntry(Date dateOfEntry) {
            employee.setDateOfEntry(dateOfEntry);
            return this;
        }

        public Employee.Builder setLogin(String login) {
            employee.setLogin(login);
            return this;
        }

        public Employee.Builder setPassword(String password) {
            employee.setPassword(password);
            return this;
        }

        public Employee get() {
            return employee;
        }
    }
}
