package com.katyshevtseva.vacationschedule.backend.exeption;

public class EntityAccessException extends EntityException {

    public EntityAccessException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage);
    }
}
