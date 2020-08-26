package com.katyshevtseva.vacationschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.katyshevtseva.vacationschedule")
@EntityScan(basePackages = "com.katyshevtseva.vacationschedule.model")
@EnableJpaRepositories("com.katyshevtseva.vacationschedule.backend.repository")
public class VacationScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(VacationScheduleApplication.class, args);
    }
}