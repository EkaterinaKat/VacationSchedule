package com.katyshevtseva.vacationschedule.backend.exeption;

public class EntityException extends Exception {
    private ExceptionMessage exceptionMessage;

    EntityException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage() {
        return exceptionMessage.getText();
    }
}
