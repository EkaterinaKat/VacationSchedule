package com.katyshevtseva.vacationschedule.backend.request;

import lombok.Data;

@Data
public class VacationsSortByPeriodRequest {

    private String beginningDate;

    private String endDate;
}
