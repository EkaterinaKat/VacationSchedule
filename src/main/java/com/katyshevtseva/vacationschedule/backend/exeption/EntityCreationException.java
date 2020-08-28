package com.katyshevtseva.vacationschedule.backend.exeption;

public class EntityCreationException extends Exception {
    private ExceptionMessage exceptionMessage;

    public EntityCreationException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage() {
        return exceptionMessage.getText();
    }
}
