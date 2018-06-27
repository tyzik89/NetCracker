package ru.vladimir.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.vladimir.springboot.entities.Visit;
import ru.vladimir.springboot.repositories.VisitsRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Создание контроллера и возврат домашней страницы
 */
@Controller
public class IndexController1 {
    @Autowired
    VisitsRepository visitsRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void createReview() {
        Visit visit = new Visit();
        visit.desription = String.format("Visited at %s", LocalDateTime.now());
        visitsRepository.save(visit);
    }
}
