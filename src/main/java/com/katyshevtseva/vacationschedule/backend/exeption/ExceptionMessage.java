package com.katyshevtseva.vacationschedule.backend.exeption;

public enum ExceptionMessage {
    PERSONNEL_NUMBER_EXISTS("Employee with this number exists"),
    LOGIN_EXISTS("Employee with this login exists"),
    INCORRECT_DATES("Incorrect dates"),
    VACATIONS_OVERLAP("New vacation overlaps with existing one"),
    NOT_FOUND("Entity not found");

    private final String text;

    ExceptionMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
