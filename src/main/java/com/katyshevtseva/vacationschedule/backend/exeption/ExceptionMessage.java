package com.katyshevtseva.vacationschedule.backend.exeption;

public enum ExceptionMessage {
    PERSONNEL_NUMBER_EXISTS("Employee with this number exists"),
    LOGIN_EXISTS("Employee with this login exists");

    private final String text;

    ExceptionMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
