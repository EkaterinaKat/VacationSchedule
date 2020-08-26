package com.katyshevtseva.vacationschedule.backend.controller;

import com.katyshevtseva.vacationschedule.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MyController {
    @Autowired
    private
    EmployeeService employeeService;

    @GetMapping("/catalogue")
    public String showCatalogue(Map<String, Object> model) {
        model.put("name", "Kate");
        employeeService.meth();
        return "catalogue";
    }
}
