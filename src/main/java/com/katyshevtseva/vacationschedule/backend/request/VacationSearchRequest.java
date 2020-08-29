package com.katyshevtseva.vacationschedule.backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VacationSearchRequest {
    private List<Long> employeeIds;

    private String beginningDate;

    private String endDate;
}
