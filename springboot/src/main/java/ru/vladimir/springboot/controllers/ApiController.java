package ru.vladimir.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vladimir.springboot.entities.Visit;
import ru.vladimir.springboot.repositories.VisitsRepository;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    VisitsRepository visitsRepository;

    //final VisitsRepository visitsRepository;
    /*public ApiController(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }*/

    @GetMapping("/visits")
    public Iterable<Visit> getVisits() {
        return visitsRepository.findAll();
    }
}
