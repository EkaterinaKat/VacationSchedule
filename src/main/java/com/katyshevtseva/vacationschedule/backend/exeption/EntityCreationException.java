package com.katyshevtseva.vacationschedule.backend.exeption;

public class EntityCreationException extends EntityException {

    public EntityCreationException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage);
    }
}
